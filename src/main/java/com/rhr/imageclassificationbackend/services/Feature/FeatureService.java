package com.rhr.imageclassificationbackend.services.Feature;
import com.rhr.imageclassificationbackend.model.Features;
import com.rhr.imageclassificationbackend.repositories.Feature.IFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService implements IFeatureService {


    private IFeatureRepository iFeatureRepository;

    @Autowired
    public FeatureService(IFeatureRepository iFeatureRepository) {
        this.iFeatureRepository = iFeatureRepository;
    }

    @Override
    public List<Features> findAll() throws Exception {
        return iFeatureRepository.findAll();
    }

}
