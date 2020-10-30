package com.rhr.imageclassificationbackend.repositories.Feature;

import com.rhr.imageclassificationbackend.model.Features;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFeatureRepository extends JpaRepository<Features, UUID> {


}
