package com.rhr.imageclassificationbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ANNModel {
    private int epochs;
    private String classifierName;
}
