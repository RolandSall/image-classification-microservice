package com.rhr.imageclassificationbackend.controllers.Login;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignInApiRequest {
    private String username;
    private String password;
}
