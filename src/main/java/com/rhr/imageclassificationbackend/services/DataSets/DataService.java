package com.rhr.imageclassificationbackend.services.DataSets;

import com.rhr.imageclassificationbackend.controllers.DataSets.DataSet;
import com.rhr.imageclassificationbackend.repositories.DataSet.IDataServiceRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService implements IDataSetService {

    private IDataServiceRepositoryDAO iDataServiceRepositoryDAO;

    @Autowired
    public DataService(IDataServiceRepositoryDAO iDataServiceRepositoryDAO) {
        this.iDataServiceRepositoryDAO = iDataServiceRepositoryDAO;
    }


    @Override
    public List<DataSet> findAll() throws Exception {
        return iDataServiceRepositoryDAO.findAll();
    }
}
