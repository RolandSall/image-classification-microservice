package com.rhr.imageclassificationbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String young;
    private String middleAge;
    private String old;
    private String youngFF;
    private String middleAgeFF;
    private String oldFF;
    private String youngMF;
    private String middleAgeMF;
    private String oldMF;
    private String youngM;
    private String middleAgeM;
    private String oldM;
    private String weightFF;
    private String weightMF;
    private String weightM;
    private String error;

}

