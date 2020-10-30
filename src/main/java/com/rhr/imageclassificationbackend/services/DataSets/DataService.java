package com.rhr.imageclassificationbackend.services.DataSets;
import com.rhr.imageclassificationbackend.model.DataSets;
import com.rhr.imageclassificationbackend.repositories.DataSet.IDataServiceRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DataService implements IDataSetService {

    private IDataServiceRepositoryDAO iDataServiceRepositoryDAO;

    @Autowired
    public DataService(IDataServiceRepositoryDAO iDataServiceRepositoryDAO) {
        this.iDataServiceRepositoryDAO = iDataServiceRepositoryDAO;
    }


    @Override
    public List<DataSets> findAll() throws Exception {
        return iDataServiceRepositoryDAO.findAll();
    }

    @Override
    public DataSets findByDatasetId(String uuid) throws Exception {
        return iDataServiceRepositoryDAO.findByDatasetId(uuid);
    }


}
