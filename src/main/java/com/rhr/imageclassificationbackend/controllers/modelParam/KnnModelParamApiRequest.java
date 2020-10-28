package com.rhr.imageclassificationbackend.controllers.modelParam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnnModelParamApiRequest {
    private double test_size;
    private int random_state;
    private int n_neighbours;
    private String weights;
    private String metric;
}
