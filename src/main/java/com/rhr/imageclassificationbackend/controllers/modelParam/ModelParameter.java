package com.rhr.imageclassificationbackend.controllers.modelParam;

import com.rhr.imageclassificationbackend.model.ModelParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/params")
public class ModelParameter {

    private final RestTemplate restTemplate;

    @Autowired
    public ModelParameter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity uploadModelParam(@RequestBody ModelParamApiRequest request){
        try {
            ModelParam modelParam = getModelParam(request);
            return ResponseEntity.status(HttpStatus.OK).body(modelParam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    private ModelParam getModelParam(ModelParamApiRequest request) {
        return ModelParam.builder()
                .points(request.getPoints())
                .radius(request.getRadius())
                .build();
    }

}
