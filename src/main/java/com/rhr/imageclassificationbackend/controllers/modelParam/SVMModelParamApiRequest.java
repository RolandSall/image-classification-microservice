package com.rhr.imageclassificationbackend.controllers.modelParam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SVMModelParamApiRequest {
    private String feature;
    private String dataset;
    private String classifierName;
    private double test_size;
    private int random_state;
    private int C;
    private int gamma;
    private int degree;
    private String kernel;
}
