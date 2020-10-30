package com.rhr.imageclassificationbackend.repositories.DataSet;
import com.rhr.imageclassificationbackend.model.Datasets;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IDataServiceRepositoryDAO extends JpaRepository<Datasets, UUID> {


}
