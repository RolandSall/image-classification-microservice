package com.rhr.imageclassificationbackend.controllers.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhr.imageclassificationbackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload/file")
@CrossOrigin
public class FileController {
    public static final String MODEL_PYTHON_SERVICE_ENDPOINT = "http://localhost:5000/predict";
    public static final String IMAGE_PATH = "C:\\Users\\User\\IdeaProjects\\ImageClassificationMicroService\\images\\";
    private final RestTemplate restTemplate;
    public static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public FileController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public ResponseEntity uploadImageViaPath(@RequestParam("imageFile") MultipartFile file,
                                             @RequestParam("imageName") String name,
                                             @RequestParam("ownModel") String ownModel,
                                             @RequestParam("clf1") String clf1,
                                             @RequestParam("clf2") String clf2,
                                             @RequestParam("clf3") String clf3
    ) {
        try {
            System.out.println(ownModel);
            Path trgtPath = Paths.get(formulatePath(name));
            System.out.println(trgtPath);
            file.transferTo(trgtPath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ClassifierRequest classifierRequest = buildImageRequest(formulatePath(name), ownModel,clf1,clf2,clf3);
            String json = mapper.writeValueAsString(classifierRequest);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            Message message = restTemplate.postForObject(MODEL_PYTHON_SERVICE_ENDPOINT, entity, Message.class);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ClassifierRequest buildImageRequest(String modifiedPath, String ownModel, String clf1, String clf2, String clf3) {
        return new ClassifierRequest().builder()
                .path(modifiedPath)
                .ownModel(ownModel)
                .clf1(clf1)
                .clf2(clf2)
                .clf3(clf3)
                .build();
    }

    private String formulatePath(@RequestParam("imageName") String name) {
        return IMAGE_PATH + name + ".jpg";
    }

    private String getModifiedPath(String path) {
        return path.replaceAll("\\\\", "\\\\\\\\");
    }

    private FileApiResponse buildResponse(String path, String output) {
        return new FileApiResponse().builder()
                .path(path)
                .output(output)
                .build();
    }
}
