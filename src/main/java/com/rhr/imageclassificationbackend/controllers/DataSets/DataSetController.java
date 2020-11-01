package com.rhr.imageclassificationbackend.controllers.DataSets;

import com.rhr.imageclassificationbackend.model.DataSets;
import com.rhr.imageclassificationbackend.services.DataSets.IDataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/datasets")
@CrossOrigin
public class DataSetController {

    private IDataSetService iDataSetService;

    @Autowired
    public DataSetController(IDataSetService iDataSetService) {
        this.iDataSetService = iDataSetService;
    }

    @GetMapping()
    public ResponseEntity findAllDataSets() {
        try {
            List<DataSets> listOfDataSets = iDataSetService.findAll();
            System.out.println(listOfDataSets);
            List<DataSetApiResponse> response = buildListOfResponse(listOfDataSets);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/{dataSetUUID}")
    public ResponseEntity findDataSetByUUID(@PathVariable("dataSetUUID") String dataSetUUID) {
        try {
            DataSets dataSet = iDataSetService.findByDatasetId(dataSetUUID);
            DataSetApiResponse response = buildResponse(dataSet);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    private List<DataSetApiResponse> buildListOfResponse(List<DataSets> listOfDataSets) {
        List<DataSetApiResponse> responseList = new ArrayList<>();
        for (DataSets dataSet : listOfDataSets) {
            responseList.add(buildResponse(dataSet));
        }
        return responseList;
    }

    private DataSetApiResponse buildResponse(DataSets dataSet) {
        return new DataSetApiResponse().builder()
                .datasetId(dataSet.getDatasetId())
                .description(dataSet.getDescription())
                .origin(dataSet.getOrigin())
                .build();

    }

}
