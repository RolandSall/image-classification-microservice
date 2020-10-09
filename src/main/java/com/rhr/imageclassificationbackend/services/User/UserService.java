package com.rhr.imageclassificationbackend.services.User;

import com.rhr.imageclassificationbackend.model.User;
import com.rhr.imageclassificationbackend.repositories.User.IUserRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private IUserRepositoryDAO iUserRepositoryDAO;

    @Autowired
    public UserService(IUserRepositoryDAO iUserRepositoryDAO) {
        this.iUserRepositoryDAO = iUserRepositoryDAO;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
