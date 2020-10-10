package com.rhr.imageclassificationbackend.controllers.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/upload/file")
public class File {

    @PostMapping
    public ResponseEntity uploadImage(@RequestBody FileApiRequest request){
        try {
            String path = request.getPath();
            // todo: implement restTemplate and built the response
            FileApiResponse response = buildResponse(path);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private FileApiResponse buildResponse(String path) {
        return new FileApiResponse().builder()
                .path(path)
                .output("Dummy Response")
                .build();
    }
}
