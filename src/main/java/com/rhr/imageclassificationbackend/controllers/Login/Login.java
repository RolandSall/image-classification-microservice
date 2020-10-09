package com.rhr.imageclassificationbackend.controllers.Login;

import com.rhr.imageclassificationbackend.model.User;
import com.rhr.imageclassificationbackend.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity signIn(@RequestBody UserSignInApiRequest request) {
        try {
            User user = userService.findUserByUsernameAndPassword(getUserName(request), getUserPassword(request));
            UserApiResponse response = getUserApiResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(e.getMessage());
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody UserSignUpApiRequest request) {
        try {
            User user = userService.createUser(getUser(request));
            UserApiResponse response = getUserApiResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(e.getMessage());
        }
    }

    private User getUser(UserSignUpApiRequest request) {
        return new User().builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .userId(request.getUserId())
                .address(request.getAddress())
                .email(request.getEmail())
                .gender(request.getGender())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();
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

    private String getUserPassword(UserSignInApiRequest request) {
        return request.getUsername();
    }

    private String getUserName(UserSignInApiRequest request) {
        return request.getPassword();
    }


}

