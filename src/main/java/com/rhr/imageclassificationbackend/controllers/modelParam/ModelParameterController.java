package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhr.imageclassificationbackend.model.ANNModel;
import com.rhr.imageclassificationbackend.model.DatasetsFeatures;
import com.rhr.imageclassificationbackend.model.KnnParam;
import com.rhr.imageclassificationbackend.model.SVMModel;
import com.rhr.imageclassificationbackend.services.DataSets.IDataSetService;
import com.rhr.imageclassificationbackend.services.Feature.IFeatureService;
import com.rhr.imageclassificationbackend.services.FeatureDatasetRepository.IFeatureDataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/params")
@CrossOrigin
public class ModelParameterController {

    private final RestTemplate restTemplate;
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final HttpHeaders headers = new HttpHeaders();
    private IFeatureDataSetService iFeatureDataSetService;
    private IFeatureService iFeatureService;
    private IDataSetService iDataSetService;
    public static final String TRAIN_LOCALHOST_ENDPOINT = "http://localhost:8000/";
    public static final String TRAIN_COLAB_ENDPOINT = "http://0cd651d2cb5b.ngrok.io/ANN";
    public static final String TRAIN_ENDPOINT = TRAIN_LOCALHOST_ENDPOINT + "/train";
    public static final String[] weightsPossibilities = {"uniform", "distance"};
    public static final String[] metricPossibilites = {"euclidean", "manhattan", "minkowski"};
    public static final String[] kernelPossibilities = {"linear", "poly", "rbf", "sigmoid"};


    @Autowired
    public ModelParameterController(RestTemplate restTemplate, IFeatureDataSetService iFeatureDataSetService, IFeatureService iFeatureService, IDataSetService iDataSetService) {
        this.restTemplate = restTemplate;
        this.iFeatureDataSetService = iFeatureDataSetService;
        this.iFeatureService = iFeatureService;
        this.iDataSetService = iDataSetService;
    }

    @PostMapping("/datasets/{dataSetUUID}/features/{featureUUID}/Knn")
    public ResponseEntity trainKnnClassifier(@PathVariable("dataSetUUID") String dataSetUUID,
                                             @PathVariable("featureUUID") String featureUUID,
                                             @RequestBody KnnModelParamApiRequest request) {
        try {
            if (isKnnValidRequest(request)) {
                String featureName = getFeatureName(featureUUID);
                String dataSetName = getDataSetDescription(dataSetUUID);
                headers.setContentType(MediaType.APPLICATION_JSON);
                KnnParam knnParam = buildJsonFromKNNRequest(request, featureName, dataSetName);
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


    @PostMapping("/datasets/{dataSetUUID}/features/{featureUUID}/Svm")
    public ResponseEntity trainSVMClassifier(@PathVariable("dataSetUUID") String dataSetUUID,
                                             @PathVariable("featureUUID") String featureUUID,
                                             @RequestBody SVMModelParamApiRequest request) {
        try {
            if (isSVMValidRequest(request)) {
                String featureName = getFeatureName(featureUUID);
                String dataSetName = getDataSetDescription(dataSetUUID);
                headers.setContentType(MediaType.APPLICATION_JSON);
                SVMModel svmModel = buildJsonFromSVMRequest(request, featureName, dataSetName);
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

    @PostMapping("/trainAnn")
    public ResponseEntity trainANN(@RequestBody ANNModelApiRequest request) {
        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            ANNModel annModel = buildJsonFromANNRequest(request);
            String json = mapper.writeValueAsString(annModel);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ANNModelApiResponse answer = restTemplate.postForObject(TRAIN_COLAB_ENDPOINT, entity, ANNModelApiResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(buildListResponse(answer));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    private ANNModelResponse buildListResponse(ANNModelApiResponse answer) {
        ArrayList accList = new ArrayList(getAccList(answer));
        ArrayList lostList = new ArrayList(getLossList(answer));
        ArrayList val_accuracyList = new ArrayList(getValAcc(answer));
        ArrayList val_lossList = new ArrayList(getValLoss(answer));
        return new ANNModelResponse().builder()
                .accuracyList(accList)
                .lossList(lostList)
                .val_accuracy(val_accuracyList)
                .val_loss(val_lossList)
                .build();
    }

    private List<String> getLossList(ANNModelApiResponse answer) {
        return Arrays.stream(((String) answer.getLoss()).substring(1,((String) answer.getLoss()).length()-1).split(", ")).collect(Collectors.toList());
    }

    private List<String> getAccList(ANNModelApiResponse answer) {
        return Arrays.stream(((String) answer.getAccuracy()).substring(1,((String) answer.getAccuracy()).length()-1).split(", ")).collect(Collectors.toList());
    }

    private List<String> getValAcc(ANNModelApiResponse answer) {
        return Arrays.stream(((String) answer.getVal_accuracy()).substring(1,((String) answer.getVal_accuracy()).length()-1).split(", ")).collect(Collectors.toList());
    }

    private List<String> getValLoss(ANNModelApiResponse answer) {
        return Arrays.stream(((String) answer.getVal_loss()).substring(1,((String) answer.getVal_loss()).length()-1).split(", ")).collect(Collectors.toList());
    }

    @GetMapping("/datasets/{dataSetUUID}/features")
    public ResponseEntity checkDataSetFeatures(@PathVariable("dataSetUUID") String dataSetUUID) {
        try {
            List<DatasetsFeatures> DatasetsFeatures = iFeatureDataSetService.findAll(dataSetUUID);
            return ResponseEntity.status(HttpStatus.OK).body(DatasetsFeatures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    private boolean isSVMValidRequest(SVMModelParamApiRequest request) {
        if (Arrays.asList(kernelPossibilities).contains(request.getKernel()) && request.getClassifierName().equals("Svm"))
            if (request.getTest_size() <= 1 && request.getTest_size() > 0)
                return true;
        return false;
    }


    private boolean isKnnValidRequest(KnnModelParamApiRequest request) {
        if (request.getTest_size() <= 1 && request.getTest_size() > 0 && request.getN_neighbours() < 100 && request.getClassifierName().equals("Knn"))
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


    private SVMModel buildJsonFromSVMRequest(SVMModelParamApiRequest request, String featureName, String dataSetName) {
        return new SVMModel().builder()
                .dataset(dataSetName)
                .feature(featureName)
                .classifierName(request.getClassifierName())
                .test_size(request.getTest_size())
                .random_state(request.getRandom_state())
                .C(request.getC())
                .degree(request.getDegree())
                .gamma(request.getGamma())
                .kernel(request.getKernel())
                .build();
    }

    private KnnParam buildJsonFromKNNRequest(KnnModelParamApiRequest request, String featureName, String dataSetName) {
        return new KnnParam().builder()
                .dataset(dataSetName)
                .feature(featureName)
                .classifierName(request.getClassifierName())
                .test_size(request.getTest_size())
                .random_state(request.getRandom_state())
                .n_neighbours(request.getN_neighbours())
                .metric(request.getMetric())
                .weights(request.getWeights())
                .build();
    }


    private String getDataSetDescription(String dataSetUUID) throws Exception {
        return iDataSetService.findByDatasetId(dataSetUUID).getDescription();
    }

    private String getFeatureName(String featureUUID) throws Exception {
        return iFeatureService.findById(featureUUID).getName();
    }

    private ANNModel buildJsonFromANNRequest(ANNModelApiRequest request) {
        return new ANNModel().builder()
                .classifierName(request.getClassifierName())
                .epochs(request.getEpochs())
                .build();
    }
}
