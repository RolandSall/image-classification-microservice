package com.rhr.imageclassificationbackend.services.DataSets;
import com.rhr.imageclassificationbackend.model.DataSets;

import java.util.List;
import java.util.UUID;

public interface IDataSetService {

    List<DataSets> findAll() throws Exception;

    DataSets findByDatasetId(String string) throws Exception;
}
