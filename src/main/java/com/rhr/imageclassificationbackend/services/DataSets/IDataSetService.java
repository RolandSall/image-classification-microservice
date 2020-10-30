package com.rhr.imageclassificationbackend.services.DataSets;
import com.rhr.imageclassificationbackend.model.DataSets;

import java.util.List;

public interface IDataSetService {

    List<DataSets> findAll() throws Exception;
}
