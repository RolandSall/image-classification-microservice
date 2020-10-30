package com.rhr.imageclassificationbackend.services.Feature;
import com.rhr.imageclassificationbackend.repositories.Feature.IFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService implements IFeatureService {


    private IFeatureRepository iFeatureRepository;

    @Autowired
    public FeatureService(IFeatureRepository iFeatureRepository) {
        this.iFeatureRepository = iFeatureRepository;
    }
}
