package com.rhr.imageclassificationbackend.services.DataSets;
import com.rhr.imageclassificationbackend.model.Datasets;

import java.util.List;

public interface IDataSetService {

    List<Datasets> findAll() throws Exception;
}
