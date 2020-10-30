package com.rhr.imageclassificationbackend.repositories.DataSet;
import com.rhr.imageclassificationbackend.controllers.DataSets.DataSet;
import com.rhr.imageclassificationbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IDataServiceRepositoryDAO extends JpaRepository<DataSet, UUID> {


}
