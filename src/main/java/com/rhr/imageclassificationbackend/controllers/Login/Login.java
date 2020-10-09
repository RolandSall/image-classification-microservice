package com.rhr.imageclassificationbackend.controllers.Login;

import com.rhr.imageclassificationbackend.model.User;
import com.rhr.imageclassificationbackend.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Login {

    private UserService userService;

    @Autowired
    public Login(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signIn")
    public ResponseEntity signUp(@RequestBody UserApiRequest request) {
        try {
            User user = userService.findUserByUsernameAndPassword(getUserName(request), getUserPassword(request));
            UserApiResponse response = getUserApiResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(e.getMessage());
        }
    }

    private UserApiResponse getUserApiResponse(User user) {
        return new UserApiResponse().builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .userId(user.getUserId())
                .address(user.getAddress())
                .email(user.getEmail())
                .gender(user.getGender())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .build();

    }

    private String getUserPassword(UserApiRequest request) {
        return request.getUsername();
    }

    private String getUserName(UserApiRequest request) {
        return request.getPassword();
    }


}

