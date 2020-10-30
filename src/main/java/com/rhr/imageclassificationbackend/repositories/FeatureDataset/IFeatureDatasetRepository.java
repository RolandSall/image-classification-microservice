package com.rhr.imageclassificationbackend.repositories.FeatureDataset;

import com.rhr.imageclassificationbackend.model.DatasetsFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IFeatureDatasetRepository extends JpaRepository<DatasetsFeatures, UUID> {
    @Query(
            value = "SELECT DISTINCT name, df.feature_id, df.dataset_id , df.id FROM features " +
                    "JOIN datasets_features df on features.feature_id = df.feature_id " +
                    "where df.dataset_id = UNHEX(REPLACE(:uuid,'-',''))",
            nativeQuery = true
    )
    List<DatasetsFeatures> findAllByDataset_id(@Param("uuid") String uuid);
}
