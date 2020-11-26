package com.rhr.imageclassificationbackend.controllers.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhr.imageclassificationbackend.controllers.modelParam.ModelScoreApiResponse;
import com.rhr.imageclassificationbackend.model.Model;
import com.rhr.imageclassificationbackend.services.Model.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ModelController {

    private IModelService iModelService;
    private final RestTemplate restTemplate;
    public static final String SAVE_KNN_ENDPOINT = "http://localhost:8000/saveKnn";
    public static final String SAVE_SVM_ENDPOINT = "http://localhost:8000/saveSvm";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public ModelController(IModelService iModelService, RestTemplate restTemplate) {
        this.iModelService = iModelService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/models")
    public ResponseEntity findAllModels() {
        try {
            List<Model> listOfModels = iModelService.findAllModels();
            List<ModelApiResponse> response = buildListOfResponse(listOfModels);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping("/models/model")
    public ResponseEntity saveModel(@RequestBody SaveModelApiRequest request) {
        try {
            System.out.println(request.getAccuracy());
            ModelSavingApiResponse modelSavingApiResponse;
            headers.setContentType(MediaType.APPLICATION_JSON);
            String json = mapper.writeValueAsString(request.getName());
            System.out.println(request.getName());
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            if (request.getClassifier().equals("Knn")) {
                modelSavingApiResponse = restTemplate.postForObject(SAVE_KNN_ENDPOINT, entity, ModelSavingApiResponse.class);
            } else if (request.getClassifier().equals("Svm")) {
                modelSavingApiResponse = restTemplate.postForObject(SAVE_SVM_ENDPOINT, entity, ModelSavingApiResponse.class);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Model to be Saved");
            }
            if (!isModelTrained(modelSavingApiResponse)) {
                Model model = iModelService.saveModel(getSavedModelFromApiRequest(request));
                ModelApiResponse response = buildResponse(model);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Model to be Saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/models/{modelId}")
    public ResponseEntity saveModel(@PathVariable("modelId") String modelId) {
        try {
            String deletedModelById = iModelService.deleteModelById(modelId);
            return ResponseEntity.status(HttpStatus.OK).body(deletedModelById);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/models/{modelId}")
    public ResponseEntity updateModelVisibility(@RequestBody UpdateModelApiRequest request,
                                                @PathVariable("modelId") String modelId) {
        try {
            String updateModelById = iModelService.updateModelById(modelId, request.isVisible());
            return ResponseEntity.status(HttpStatus.OK).body(updateModelById);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private Model getSavedModelFromApiRequest(SaveModelApiRequest request) {
        return new Model().builder()
                .classifier(request.getClassifier())
                .feature(request.getFeature())
                .dataset(request.getDataset())
                .owner(request.getOwner())
                .name(request.getName())
                .accuracy(request.getAccuracy())
                .visible(false)
                .build();
    }

    private List<ModelApiResponse> buildListOfResponse(List<Model> listOfModels) {
        List<ModelApiResponse> responseList = new ArrayList<>();
        for (Model model : listOfModels) {
            responseList.add(buildResponse(model));
        }
        return responseList;
    }

    private ModelApiResponse buildResponse(Model model) {
        return new ModelApiResponse().builder()
                .modelId(model.getModelId())
                .classifier(model.getClassifier())
                .feature(model.getFeature())
                .dataset(model.getDataset())
                .owner(model.getOwner())
                .name(model.getName())
                .accuracy(model.getAccuracy())
                .visible(model.isVisible())
                .build();

    }

    private boolean isModelTrained(ModelSavingApiResponse modelSavingApiResponse) {
        return modelSavingApiResponse.getMessage().equals("No Model To Be Saved");
    }
}
