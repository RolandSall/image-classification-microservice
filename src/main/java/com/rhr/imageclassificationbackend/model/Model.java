package com.rhr.imageclassificationbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelId;
    private String owner;
    private String dataset;
    private String classifier;
    private String feature;
    private String name;
    private boolean visible;
    private String accuracy;
}
