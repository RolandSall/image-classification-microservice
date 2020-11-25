package com.rhr.imageclassificationbackend.controllers.Model;

import com.rhr.imageclassificationbackend.controllers.DataSets.DataSetApiResponse;
import com.rhr.imageclassificationbackend.model.DataSets;
import com.rhr.imageclassificationbackend.model.Model;
import com.rhr.imageclassificationbackend.services.Model.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ModelController {

    private IModelService iModelService;

    @Autowired
    public ModelController(IModelService iModelService) {
        this.iModelService = iModelService;
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
            Model model = iModelService.saveModel(getSavedModelFromApiRequest(request));
            ModelApiResponse response = buildResponse(model);
            return ResponseEntity.status(HttpStatus.OK).body(response);
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
            String updateModelById = iModelService.updateModelById(modelId,request.isVisible());
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
                .visible(model.isVisible())
                .build();

    }
}
