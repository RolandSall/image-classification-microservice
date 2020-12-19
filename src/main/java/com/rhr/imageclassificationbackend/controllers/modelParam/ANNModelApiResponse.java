package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ANNModelApiResponse {
    @JsonProperty("accuracy")
    private Object accuracy;
    @JsonProperty("loss")
    private Object loss;
    @JsonProperty("val_loss")
    private Object val_loss;
    @JsonProperty("val_accuracy")
    private Object val_accuracy;

}
