package com.rhr.imageclassificationbackend.controllers.Login;

import com.rhr.imageclassificationbackend.model.Admin;
import com.rhr.imageclassificationbackend.model.User;
import com.rhr.imageclassificationbackend.services.Admin.AdminService;
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
    private AdminService adminService;

    @Autowired
    public Login(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @PostMapping("/signIn")
    public ResponseEntity signInAsAdmin(@RequestBody UserAndAdminApiRequest request) {
        try {
            Admin admin = adminService.findAdminByUsernameAndPassword(getUserName(request), getPassword(request));
            AdminApiResponse response = getAdminApiResponse(admin);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity signInAsUser(@RequestBody UserAndAdminApiRequest request) {
        try {
            User user = userService.findUserByUsernameAndPassword(getUserName(request), getPassword(request));
            UserApiResponse response = getUserApiResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody UserSignUpApiRequest request) {
        try {
            User user = userService.createUser(getUser(request));
            UserApiResponse response = getUserApiResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private User getUser(UserSignUpApiRequest request) {
        return new User().builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
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

    private AdminApiResponse getAdminApiResponse(Admin admin) {
        return new AdminApiResponse().builder()
                .adminId(admin.getAdminId())
                .username(admin.getUsername())
                .password(admin.getPassword())
                .email(admin.getEmail())
                .build();

    }


    private String getPassword(UserAndAdminApiRequest request) {
        return request.getPassword();
    }

    private String getUserName(UserAndAdminApiRequest request) {
        return request.getUsername();
    }


}
