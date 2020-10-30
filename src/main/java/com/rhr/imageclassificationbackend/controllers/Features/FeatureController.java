package com.rhr.imageclassificationbackend.controllers.Features;

import com.rhr.imageclassificationbackend.controllers.DataSets.DataSetApiResponse;
import com.rhr.imageclassificationbackend.model.DataSets;
import com.rhr.imageclassificationbackend.model.Features;
import com.rhr.imageclassificationbackend.services.DataSets.IDataSetService;
import com.rhr.imageclassificationbackend.services.Feature.IFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FeatureController {

    private IFeatureService iFeatureService;

    @Autowired
    public FeatureController(IFeatureService iFeatureService) {
        this.iFeatureService = iFeatureService;
    }

    @GetMapping("/features")
    public ResponseEntity findAllDataSets() {
        try {
            List<Features> listofFeatures = iFeatureService.findAll();
            List<FeatureApiResponse> response = buildListOfResponse(listofFeatures);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    private List<FeatureApiResponse> buildListOfResponse(List<Features> listofFeatures) {
        List<FeatureApiResponse> responseList = new ArrayList<>();
        for (Features feature : listofFeatures) {
            responseList.add(buildResponse(feature));
        }
        return responseList;
    }

    private FeatureApiResponse buildResponse(Features feature) {
        return new FeatureApiResponse().builder()
                .featureId(feature.getFeatureId())
                .name(feature.getName())
                .build();

    }


}
