package com.rhr.imageclassificationbackend.controllers.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/upload/file")
public class File {

    public static final String MODEL_PYTHON_SERVICE_ENDPOINT = "http://localhost:5000/predict";
    private final RestTemplate restTemplate;

    @Autowired
    public File(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity uploadImage(@RequestBody FileApiRequest request){
        try {
            String modifiedPath = getModifiedPath(request);
            String url = MODEL_PYTHON_SERVICE_ENDPOINT;
            String requestJson = buildJsonFromRequest(modifiedPath);
            System.out.println(requestJson);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
            String answer = restTemplate.postForObject(url, entity, String.class);
            FileApiResponse response = buildResponse(request.getPath(),answer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    private String buildJsonFromRequest(String modifiedPath) {
        return "{\"path\": " + "\"" + modifiedPath + "\"" + "}";
    }


    private String getModifiedPath(FileApiRequest request) {
        return request.getPath().replaceAll("\\\\", "\\\\\\\\");
    }

    private FileApiResponse buildResponse(String path, String output) {
        return new FileApiResponse().builder()
                .path(path)
                .output(output)
                .build();
    }
}
