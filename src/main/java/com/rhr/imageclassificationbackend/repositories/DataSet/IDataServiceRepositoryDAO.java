package com.rhr.imageclassificationbackend.repositories.DataSet;

import com.rhr.imageclassificationbackend.model.DataSets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IDataServiceRepositoryDAO extends JpaRepository<DataSets, UUID> {

    @Query(
            value = "SELECT * FROM data_sets where dataset_id = " +
                    "UNHEX(REPLACE(:uuid,'-',''))",
            nativeQuery = true
    )
    DataSets findByDatasetId(@Param("uuid") String uuid);

}
