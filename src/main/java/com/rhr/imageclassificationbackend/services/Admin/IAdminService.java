package com.rhr.imageclassificationbackend.services.Admin;

import com.rhr.imageclassificationbackend.model.Admin;
import com.rhr.imageclassificationbackend.model.User;

public interface IAdminService {

    Admin findAdminByUsernameAndPassword(String username, String password) throws Exception;
}
