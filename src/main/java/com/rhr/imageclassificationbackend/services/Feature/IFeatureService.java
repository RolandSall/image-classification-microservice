package com.rhr.imageclassificationbackend.services.Feature;


import com.rhr.imageclassificationbackend.model.DataSets;
import com.rhr.imageclassificationbackend.model.Features;

import java.util.List;

public interface IFeatureService {

    List<Features> findAll() throws Exception;

    Features findById(String uuid) throws Exception;

   }
