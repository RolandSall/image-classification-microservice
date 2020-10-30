package com.rhr.imageclassificationbackend.services.FeatureDatasetRepository;
import com.rhr.imageclassificationbackend.model.DatasetsFeatures;

import java.util.List;

public interface IFeatureDataSetService {


    List<DatasetsFeatures> findAll(String dataSetUUID) throws Exception;

   }
