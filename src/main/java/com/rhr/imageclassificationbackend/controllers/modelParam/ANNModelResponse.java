package com.rhr.imageclassificationbackend.controllers.modelParam;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ANNModelResponse {
    private ArrayList accuracyList;
    private ArrayList lossList;

}
