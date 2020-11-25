package com.rhr.imageclassificationbackend.controllers.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ModelApiResponse {
    private int modelId;
    private String owner;
    private String dataset;
    private String classifier;
    private String feature;
    private String name;
    private boolean visible;
}
