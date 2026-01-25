package com.userservice.service.impl;

import com.userservice.client.HotelFeignClient;
import com.userservice.client.RatingFeignClient;
import com.userservice.client.WebClientConfig;
import com.userservice.dto.Hotel;
import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
import com.userservice.exception.exceptionhandler.UserCreateException;
import com.userservice.exception.exceptionhandler.UserNotFoundException;
import com.userservice.mapper.UserMapper;
//import com.userservice.model.Address;
import com.userservice.model.Rating;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WebClient.Builder webClient;
    private final HotelFeignClient hotelFeignClient;
    private final RatingFeignClient ratingFeignClient;

    @Value("${rating.getByUserId}")
    String getUserRatingServiceURL;

    @Value("${rating.hotelURL}")
    String hotelUrl;

    @Override
    public UserResponse getUserById(String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()) {
                // fetch http://localhost:9094/api/rating/186575a1-d4d6-4332-ad4a-bddbaa044176
                //Get User Rating
               List<Rating> userRating = fetchUserRating(user.get().getUserId());
                return userMapper.addRatingByEntity(user.get(),userRating);
            } else {
                throw new UserNotFoundException("User not exits with id " + id);
            }
        } catch (Exception e) {
            log.error("User fetch exception with id {} ",id);
            throw new UserNotFoundException("User not exits with id " + id);
        }
    }

    private List<Rating> fetchUserRating(String userId) {
        String url = getUserRatingServiceURL + userId;
        List<Rating> ratings = new ArrayList<>();
        try {
//            ratings = webClient.build().get()
//                    .uri(url)
//                    .retrieve()
//                    .bodyToFlux(Rating.class)
//                    .collectList()
//                    .block();
            ratings = ratingFeignClient.getUserRatingsById(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            if(e.getMessage().contains("404")) {
            log.info("Empty Response for id");
            }
        }
        if(ratings.size() > 1) {
            ratings.stream().map(this::getHotelDetails).collect(Collectors.toList());
        }
        return ratings;
    }

    private Rating getHotelDetails(Rating rating) {
//        String url = hotelUrl + rating.getHotelId();
//        Hotel hotel = webClient.build().get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(Hotel.class)
//                .block();
//        rating.setHotel(hotel);
        Hotel hotel = hotelFeignClient.getHotel(rating.getHotelId());
        rating.setHotel(hotel);
        return rating;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
            List<User> userExits = userRepository.findByEmail(request.getEmail());
            if(CollectionUtils.isEmpty(userExits)) {
                User user = userMapper.mapUserRequestToUser(request);
                User saveResponse = userRepository.save(user);
                if (!ObjectUtils.isEmpty(saveResponse)) {
                    return userMapper.mapUserToUserResponse(saveResponse);
                } else {
                    throw new UserCreateException("Unable to create User");
                }
            } else {
                throw new UserCreateException("User Already Exits with email id "+request.getEmail()+" Please try with another email");
            }
    }

    @Override
    public List<UserResponse> fetchAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if(!CollectionUtils.isEmpty(users)) {
                // Fatch User Rating
                return users.stream()
                        .map(user ->
                                userMapper.addRatingByEntity(user, fetchUserRating(user.getUserId())))
                        .toList();
            } else {
                throw new UserNotFoundException("User Not Found");
            }
        } catch (Exception e) {
            throw new UserNotFoundException("User Not Found "+e.getMessage());
        }
    }

//    @Async
//    @Override
//    public CompletableFuture<List<User>> createUserByCSV(MultipartFile file) {
//        long startTime = System.currentTimeMillis();
//        List<User> users = parseCSVtoUserObj(file);
//        log.info("Saving list of users of cound {} Thread name {}",users.size(),Thread.currentThread().getName());
//        List<User> savedUsers = userRepository.saveAll(users);
//        long endTime = System.currentTimeMillis();
//        log.info("Process file time {}",endTime-startTime);
//        return CompletableFuture.completedFuture(savedUsers);
//    }

//    @Async
//    public CompletableFuture<List<User>> findAllUsers() {
//        log.info("Find all users by completableFuture Thread name {}",Thread.currentThread().getName());
//        List<User> userList = userRepository.findAll();
//        return CompletableFuture.completedFuture(userList);
//    }

//    @Override
//    public User registerUser(User user) {
//        return userRepository.save(user);
//    }

//    @Override
//    public User getUserByRelation(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.orElseThrow();
//    }

//    private List<User> parseCSVtoUserObj(final MultipartFile file) {
//        final List<User> users = new ArrayList<>();
//        try {
//            try(final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
//                String line;
//                while((line=br.readLine()) != null) {
//                    final String[] data = line.split(",");
//                    final User user = new User();
//                    user.setFullName(data[0]);
//                    user.setUserType(data[1]);
//                    user.setEmail(data[2]);
//                    user.setGender(data[3]);
//                    user.setPhone(data[4]);
//                    user.setPassword(data[5]);
//                    users.add(user);
//                }
//                return users;
//            }
//        } catch (Exception e) {
//            throw new UserCreateException("CSV Read issue");
//        }
//    }
}
