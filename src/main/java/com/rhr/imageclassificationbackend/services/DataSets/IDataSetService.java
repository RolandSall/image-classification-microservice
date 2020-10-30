package com.rhr.imageclassificationbackend.services.DataSets;

import com.rhr.imageclassificationbackend.controllers.DataSets.DataSet;

import java.util.List;

public interface IDataSetService {

    List<DataSet> findAll() throws Exception;
}
