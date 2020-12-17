package com.rhr.imageclassificationbackend.services.Model;

import com.rhr.imageclassificationbackend.model.Model;
import com.rhr.imageclassificationbackend.repositories.Model.IModelRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService implements IModelService {

    private IModelRepositoryDAO iModelRepositoryDAO;

    @Autowired
    public ModelService(IModelRepositoryDAO iModelRepositoryDAO) {
        this.iModelRepositoryDAO = iModelRepositoryDAO;
    }

    @Override
    public List<Model> findAllModels() {
        return iModelRepositoryDAO.findAll();
    }

    @Override
    public Model saveModel(Model model) {
        return iModelRepositoryDAO.save(model);
    }

    @Override
    public String deleteModelById(String modelId) throws Exception {
        Optional<Model> modelToBeDeleted = iModelRepositoryDAO.findById(Integer.parseInt(modelId));
        if (modelToBeDeleted.isPresent()) {
            iModelRepositoryDAO.delete(modelToBeDeleted.get());
            return modelId;
        } else {
            throw new Exception("Model not found");
        }

    }

    @Override
    public String updateModelById(String modelId, boolean visible) throws Exception {
        Optional<Model> modelToBeUpdatedOptional = iModelRepositoryDAO.findById(Integer.parseInt(modelId));
        if (modelToBeUpdatedOptional.isPresent()) {
            Model model = modelToBeUpdatedOptional.get();
            model.setVisible(visible);
            iModelRepositoryDAO.save(model);
            return modelId;
        } else {
            throw new Exception("Model not found");
        }

    }
}
