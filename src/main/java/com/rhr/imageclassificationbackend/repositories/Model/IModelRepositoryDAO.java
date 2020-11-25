package com.rhr.imageclassificationbackend.repositories.Model;


import com.rhr.imageclassificationbackend.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IModelRepositoryDAO extends JpaRepository<Model,Integer> {
}
