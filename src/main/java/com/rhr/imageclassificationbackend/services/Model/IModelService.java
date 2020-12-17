package com.rhr.imageclassificationbackend.services.Model;

import com.rhr.imageclassificationbackend.model.Model;

import java.util.List;

public interface IModelService {
    List<Model> findAllModels();

    Model saveModel(Model model);

    String deleteModelById(String modelId) throws Exception;

    String updateModelById(String modelId, boolean visible) throws Exception;
}
