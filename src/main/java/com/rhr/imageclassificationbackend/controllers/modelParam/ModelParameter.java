package com.rhr.imageclassificationbackend.controllers.modelParam;

import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

}
