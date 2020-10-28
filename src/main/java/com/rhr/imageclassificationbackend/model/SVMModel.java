package com.rhr.imageclassificationbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SVMModel {
    private double test_size;
    private int random_state;
    private int C;
    private int gamma;
    private int degree;
    private String kernel;
}
