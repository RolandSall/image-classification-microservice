package com.rhr.imageclassificationbackend.services.Model;

import com.rhr.imageclassificationbackend.model.Model;
import com.rhr.imageclassificationbackend.repositories.Model.IModelRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
