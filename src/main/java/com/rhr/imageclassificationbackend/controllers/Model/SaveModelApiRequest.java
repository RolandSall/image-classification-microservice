package com.rhr.imageclassificationbackend.controllers.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaveModelApiRequest {
    private String owner;
    private String dataset;
    private String classifier;
    private String feature;
    private String name;


}
