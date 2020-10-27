package com.rhr.imageclassificationbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnnParam {
    private int n_splits;
    private int n_repeats;
    private int random_state;
    private int n_neighbours;
    private String weights;
    private String metric;

}
