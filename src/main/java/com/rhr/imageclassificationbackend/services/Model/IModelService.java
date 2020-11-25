package com.rhr.imageclassificationbackend.services.Model;

import com.rhr.imageclassificationbackend.model.Model;

import java.util.List;

public interface IModelService {
    List<Model> findAllModels();
}
