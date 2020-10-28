package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhr.imageclassificationbackend.model.KnnParam;
import com.rhr.imageclassificationbackend.model.SVMModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/params")
public class ModelParameter {

    private final RestTemplate restTemplate;
    public static final String TRAIN_COLAB_ENDPOINT = "http://cbbfad3db5d3.ngrok.io/";
    public static final String TRAIN_ENDPOINT = TRAIN_COLAB_ENDPOINT + "/train";
    public static final String[] weightsPossibilities = {"uniform", "distance"};
    public static final String[] metricPossibilites = {"euclidean", "manhattan", "minkowski"};
    public static final String[] kernelPossibilities = {"linear", "poly", "rbf", "sigmoid"};
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public ModelParameter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/knn")
    public ResponseEntity trainKnnClassifier(@RequestBody KnnModelParamApiRequest request) {
        try {
            if (isKnnValidRequest(request)) {
                headers.setContentType(MediaType.APPLICATION_JSON);
                KnnParam knnParam = buildJsonFromKNNRequest(request);
                String json = mapper.writeValueAsString(knnParam);
                HttpEntity<String> entity = new HttpEntity<>(json, headers);
                ModelScoreApiResponse answer = restTemplate.postForObject(TRAIN_ENDPOINT, entity, ModelScoreApiResponse.class);
                return ResponseEntity.status(HttpStatus.OK).body(answer.getKnnScore());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request Parameters");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/svm")
    public ResponseEntity trainSVMClassifier(@RequestBody SVMModelParamApiRequest request) {
        try {
            if (isSVMValidRequest(request)) {
                headers.setContentType(MediaType.APPLICATION_JSON);
                SVMModel svmModel = buildJsonFromSVMRequest(request);
                String json = mapper.writeValueAsString(svmModel);
                HttpEntity<String> entity = new HttpEntity<>(json, headers);
                ModelScoreApiResponse answer = restTemplate.postForObject(TRAIN_ENDPOINT, entity, ModelScoreApiResponse.class);
                return ResponseEntity.status(HttpStatus.OK).body(answer.getSVMScore());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request Parameters");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private boolean isSVMValidRequest(SVMModelParamApiRequest request) {
        if (Arrays.asList(kernelPossibilities).contains(request.getKernel()))
            if (request.getTest_size() <= 1 && request.getTest_size() > 0)
                return true;
        return false;
    }


    private boolean isKnnValidRequest(KnnModelParamApiRequest request) {
        if (request.getTest_size() <= 1 && request.getTest_size() > 0 && request.getN_neighbours() < 100)
            if (Arrays.asList(weightsPossibilities).contains(request.getWeights()) &&
                    Arrays.asList(metricPossibilites).contains(request.getMetric()))
                return true;
        return false;
    }

    private ModelScoreApiResponse buildResponse(String answer) {
        return new ModelScoreApiResponse().builder()
                .KnnScore(answer)
                .build();
    }


    private SVMModel buildJsonFromSVMRequest(SVMModelParamApiRequest request) {
        return new SVMModel().builder()
                .classifierName(request.getClassifierName())
                .test_size(request.getTest_size())
                .random_state(request.getRandom_state())
                .C(request.getC())
                .degree(request.getDegree())
                .gamma(request.getGamma())
                .kernel(request.getKernel())
                .build();
    }

    private KnnParam buildJsonFromKNNRequest(KnnModelParamApiRequest request) {
        return new KnnParam().builder()
                .classifierName(request.getClassifierName())
                .test_size(request.getTest_size())
                .random_state(request.getRandom_state())
                .n_neighbours(request.getN_neighbours())
                .metric(request.getMetric())
                .weights(request.getWeights())
                .build();
    }

}
