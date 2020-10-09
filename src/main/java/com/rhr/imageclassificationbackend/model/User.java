package com.rhr.imageclassificationbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private  int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerEmail;
    private String customerGender;
    private String customerAddress;
    private String customerUsername;
    private String customerPassword;
}
