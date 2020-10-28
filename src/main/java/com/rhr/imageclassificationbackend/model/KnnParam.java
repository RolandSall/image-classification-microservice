package com.rhr.imageclassificationbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnnParam {
    private String classifierName;
    private double test_size;
    private int random_state;
    private int n_neighbours;
    private String weights;
    private String metric;

}
