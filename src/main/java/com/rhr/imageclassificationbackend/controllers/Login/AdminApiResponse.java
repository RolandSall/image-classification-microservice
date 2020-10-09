package com.rhr.imageclassificationbackend.controllers.Login;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminApiResponse {
    private UUID adminId;
    private String username;
    private String password;
    private String email;
}
