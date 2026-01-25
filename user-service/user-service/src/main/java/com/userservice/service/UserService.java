package com.userservice.service;

import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
import com.userservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    UserResponse getUserById(String id);

    UserResponse createUser(UserRequest request);

    List<UserResponse> fetchAllUsers();

//    CompletableFuture<List<User>> createUserByCSV(MultipartFile multipartFile);
//
//    CompletableFuture<List<User>> findAllUsers();
//
//    User registerUser(User user);
//
//    User getUserByRelation(Long id);

}
