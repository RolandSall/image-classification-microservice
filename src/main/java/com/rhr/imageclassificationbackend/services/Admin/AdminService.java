package com.rhr.imageclassificationbackend.services.Admin;

import com.rhr.imageclassificationbackend.model.Admin;
import com.rhr.imageclassificationbackend.model.User;
import com.rhr.imageclassificationbackend.repositories.Admin.IAdminRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    private IAdminRepositoryDAO iAdminRepositoryDAO;

    @Autowired
    public AdminService(IAdminRepositoryDAO iAdminRepositoryDAO) {
        this.iAdminRepositoryDAO = iAdminRepositoryDAO;
    }

    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) throws Exception {
        Admin admin = iAdminRepositoryDAO.findByUsernameAndPassword(username, password);
        if(admin != null){
            return admin;
        } else{
            throw new Exception("Admin Not Found!");
        }
    }
}
