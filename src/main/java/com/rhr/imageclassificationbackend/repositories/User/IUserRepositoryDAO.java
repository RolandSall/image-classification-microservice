package com.rhr.imageclassificationbackend.repositories.User;

import com.rhr.imageclassificationbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepositoryDAO extends JpaRepository<User, UUID> {

    User findByUsernameAndPassword(String userName, String password);
    User findByUsername(String userName);
    User findByEmail(String email);


}
