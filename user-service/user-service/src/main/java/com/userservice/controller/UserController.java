package com.userservice.controller;

import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
import com.userservice.model.User;
import com.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    int count=1;
    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
      log.info("Get User by id {}",id);
      System.out.println("Count "+count);
      count++;
      return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        log.info("Create user with request {} ",request.toString());
        return new ResponseEntity<>(userService.createUser(request),HttpStatus.CREATED);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Fetch All Users");
        return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }

//    @PostMapping(value = "/csv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
//    public ResponseEntity<String> createUsersByCSV(@RequestParam(value = "files") MultipartFile[] files) {
//        List<CompletableFuture<List<User>>> list = Arrays.stream(files).map(userService::createUserByCSV).toList();
//        return new ResponseEntity<>("user created",HttpStatus.OK);
//    }

//    @GetMapping(value = "/newUsers", produces = "application/json")
//    public ResponseEntity<List<User>> getAllUserByThread() {
//        CompletableFuture<List<User>> user1 = userService.findAllUsers();
//        CompletableFuture<List<User>> user2 = userService.findAllUsers();
//        CompletableFuture<List<User>> user3 = userService.findAllUsers();
//        CompletableFuture.allOf(user1,user2,user3).join();
//
//        List<User> userData = Stream.of(user1,user2,user3)
//                .flatMap(future -> future.join().stream())
//                .toList();
//
//        return new ResponseEntity<>(userData,HttpStatus.OK);
//    }

//    @PostMapping("/registerUser")
//    public User userCreate(@RequestBody User user) {
//        User userRespone = userService.registerUser(user);
//        return userRespone;
//    }
//
//    @GetMapping("/getUserByRelation/{id}")
//    public User getUserData(@PathVariable Long id) {
//        User userRespone = userService.getUserByRelation(id);
//        return userRespone;
//    }

//     creating fallback method for circuitbreaker
    public ResponseEntity<UserResponse> ratingHotelFallback(String id,Throwable ex) {
        log.info("Fall back is called with id {} error is {}",id,ex.getMessage());
        UserResponse userResponse = UserResponse.builder()
                .userId("dummyId")
                .about("dummy about us")
                .email("dummy@mail.com")
                .name("dummy name")
                .build();
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

}
