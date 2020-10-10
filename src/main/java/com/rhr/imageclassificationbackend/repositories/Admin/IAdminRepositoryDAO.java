package com.rhr.imageclassificationbackend.repositories.Admin;

import com.rhr.imageclassificationbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAdminRepositoryDAO extends JpaRepository<Admin, UUID> {

    Admin findByUsernameAndPassword(String username, String password);


}
