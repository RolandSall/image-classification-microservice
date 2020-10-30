package com.rhr.imageclassificationbackend.controllers.Features;

import com.rhr.imageclassificationbackend.model.DataSets;
import lombok.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FeatureApiResponse {
    private UUID featureId;
    private String name;
    private List<DataSets> dataSetsArrayList = new ArrayList<>();
}
