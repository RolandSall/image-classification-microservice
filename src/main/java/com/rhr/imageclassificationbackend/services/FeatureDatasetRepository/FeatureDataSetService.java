package com.rhr.imageclassificationbackend.services.FeatureDatasetRepository;
import com.rhr.imageclassificationbackend.model.DatasetsFeatures;
import com.rhr.imageclassificationbackend.repositories.FeatureDataset.IFeatureDatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureDataSetService implements IFeatureDataSetService {

    IFeatureDatasetRepository iFeatureDatasetRepository;

    @Autowired
    public FeatureDataSetService(IFeatureDatasetRepository iFeatureDatasetRepository) {
        this.iFeatureDatasetRepository = iFeatureDatasetRepository;
    }

    @Override
    public List<DatasetsFeatures> findAll(String dataSetUUID) throws Exception {
        return iFeatureDatasetRepository.findAllByDataset_id(dataSetUUID);
    }
}
