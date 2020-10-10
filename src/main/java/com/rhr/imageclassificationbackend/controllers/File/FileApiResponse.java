package com.rhr.imageclassificationbackend.controllers.File;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FileApiResponse {
    private String path;
    private String output;
}
