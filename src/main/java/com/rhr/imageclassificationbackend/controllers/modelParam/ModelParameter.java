package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.rhr.imageclassificationbackend.controllers.File.FileApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/params")
public class ModelParameter {

    private final RestTemplate restTemplate;
    public static final String TRAIN_COLAB_ENDPOINT = "http://localhost:5000/predict";

    @Autowired
    public ModelParameter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/knn")
    public ResponseEntity trainKnnClassifier(@RequestBody KnnModelParamApiRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestJson = buildJsonFromKNNRequest(request);

            //HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            //String answer = restTemplate.postForObject(TRAIN_COLAB_ENDPOINT, entity, String.class);
            //ModelScoreApiResponse response = buildResponse(answer);
            return ResponseEntity.status(HttpStatus.OK).body("response");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ModelScoreApiResponse buildResponse(String answer) {
        return new ModelScoreApiResponse().builder()
                .score(answer)
                .build();
    }

    private String buildJsonFromKNNRequest(KnnModelParamApiRequest request) {
        return "";
    }

}
