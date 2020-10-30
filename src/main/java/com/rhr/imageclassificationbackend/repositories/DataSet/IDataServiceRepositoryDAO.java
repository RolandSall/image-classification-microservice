package com.rhr.imageclassificationbackend.repositories.DataSet;
import com.rhr.imageclassificationbackend.model.DataSets;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IDataServiceRepositoryDAO extends JpaRepository<DataSets, UUID> {


}
