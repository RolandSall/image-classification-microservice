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
        return iUserRepositoryDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public User createUser(User user) throws Exception {
        if (isValidUserName(user.getUsername())) {
            throw new Exception("Username Already Exist");
        } else if (isValidEmail(user.getEmail())) {
            throw new Exception("Email Already Exist");
        } else {
            return iUserRepositoryDAO.save(user);
        }
    }

    private boolean isValidUserName(String username){
        if (iUserRepositoryDAO.findByUsername(username) == null)
            return false;
        return true;
    }

    private boolean isValidEmail(String email){
        if (iUserRepositoryDAO.findByEmail(email) == null)
            return false;
        return true;
    }
}
