package com.rhr.imageclassificationbackend.repositories.Feature;

import com.rhr.imageclassificationbackend.model.DataSets;
import com.rhr.imageclassificationbackend.model.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IFeatureRepository extends JpaRepository<Features, UUID> {

    /*@Query(
            value = "SELECT * FROM data_sets where dataset_id = " +
                    "UNHEX(REPLACE(:uuid,'-',''))",
            nativeQuery = true
    )
    Features findByDatasetId(@Param("uuid") String uuid);*/

}
