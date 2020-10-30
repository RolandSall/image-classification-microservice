package com.rhr.imageclassificationbackend.controllers.DataSets;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DataSetApiResponse {
    private UUID datasetId;
    private String origin;
    private String description;
}
