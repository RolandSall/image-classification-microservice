package com.rhr.imageclassificationbackend.controllers.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelSavingApiResponse {
    @JsonProperty("message")
    private String message;
}
