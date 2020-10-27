package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelScoreApiResponse {
    @JsonProperty("score")
    private String score;
}
