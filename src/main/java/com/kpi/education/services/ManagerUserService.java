package com.kpi.education.services;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.dao.ManagerUserDAO;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerUserService implements UserService<ManagerUser> {


    private ManagerUserDAO managerUserDAO;

    @Autowired
    public void setManagerUserDAO(ManagerUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }


    @Override
    @Transactional(noRollbackFor = DuplicatedKeyException.class)
    public ManagerUser create(ManagerUser object) {
        //code to check for right email
        ManagerUser managerUser = managerUserDAO.create(object);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerUser get(Integer key) {
        ManagerUser managerUser = managerUserDAO.get(key);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerUser getByEmain(String email) {
        ManagerUser managerUser = managerUserDAO.getByEmail(email);
        return managerUser;
    }
    
    @Override
    @Transactional
    public ManagerUser update(ManagerUser object) {
        ManagerUser managerUser = managerUserDAO.update(object);
        return managerUser;
    }

    @Override
    @Transactional
    public boolean delete(ManagerUser object) {
        boolean res = managerUserDAO.delete(object);
        return res;
    }
}
