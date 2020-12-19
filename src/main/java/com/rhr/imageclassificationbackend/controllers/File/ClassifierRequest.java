package com.rhr.imageclassificationbackend.controllers.File;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassifierRequest {
    private String path;
    private String ownModel;
    private String clf1;
    private String clf2;
    private String clf3;
}
