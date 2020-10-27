package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhr.imageclassificationbackend.controllers.File.FileApiResponse;
import com.rhr.imageclassificationbackend.model.KnnParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/params")
public class ModelParameter {

    private final RestTemplate restTemplate;
    public static final String TRAIN_COLAB_ENDPOINT = "http://7c7e34945586.ngrok.io";

    @Autowired
    public ModelParameter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/knn")
    public ResponseEntity trainKnnClassifier(@RequestBody KnnModelParamApiRequest request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            KnnParam knnParam = buildJsonFromKNNRequest(request);
            String json = mapper.writeValueAsString(knnParam);
            System.out.println(json);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ModelScoreApiResponse answer = restTemplate.postForObject(TRAIN_COLAB_ENDPOINT, entity, ModelScoreApiResponse.class);
            System.out.println(answer.getScore());
            return ResponseEntity.status(HttpStatus.OK).body(answer.getScore());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private ModelScoreApiResponse buildResponse(String answer) {
        return new ModelScoreApiResponse().builder()
                .score(answer)
                .build();
    }

    private KnnParam buildJsonFromKNNRequest(KnnModelParamApiRequest request) {
        return new KnnParam().builder()
                .n_splits(request.getN_splits())
                .n_repeats(request.getN_repeats())
                .random_state(request.getRandom_state())
                .n_neighbours(request.getN_neighbours())
                .metric(request.getMetric())
                .weights(request.getWeights())
                .build();
    }

}
