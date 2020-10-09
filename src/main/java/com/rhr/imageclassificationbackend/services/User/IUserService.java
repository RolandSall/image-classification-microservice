package com.rhr.imageclassificationbackend.services.User;

import com.rhr.imageclassificationbackend.model.User;

public interface IUserService {

    User findUserByUsernameAndPassword(String username, String password);

    User createUser(User user);
}
