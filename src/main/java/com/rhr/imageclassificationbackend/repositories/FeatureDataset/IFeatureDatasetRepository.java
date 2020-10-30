package com.rhr.imageclassificationbackend.repositories.FeatureDataset;

import com.rhr.imageclassificationbackend.model.FeatureDataset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFeatureDatasetRepository extends JpaRepository<FeatureDataset, UUID> {


}
