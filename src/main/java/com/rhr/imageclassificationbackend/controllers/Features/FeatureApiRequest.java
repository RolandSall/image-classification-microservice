package com.rhr.imageclassificationbackend.controllers.Features;

import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FeatureApiRequest {
    private UUID datasetId;
}
