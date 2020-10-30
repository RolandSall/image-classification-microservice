package com.rhr.imageclassificationbackend.services.DataSets;
import com.rhr.imageclassificationbackend.model.Datasets;
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
    public List<Datasets> findAll() throws Exception {
        return iDataServiceRepositoryDAO.findAll();
    }
}
