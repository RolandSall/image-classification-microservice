package com.rhr.imageclassificationbackend.controllers.modelParam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelParamApiRequest {
    private int radius;
    private int points;
}
