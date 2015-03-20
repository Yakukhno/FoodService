package com.foodservice.services;

import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.dao.ShopAdminUserDAO;
import com.foodservice.exceptions.DuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopAdminUserService implements UserService<ShopAdminUser> {


    private ShopAdminUserDAO managerUserDAO;

    @Autowired
    public void setManagerUserDAO(ShopAdminUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }


    @Override
    @Transactional(noRollbackFor = DuplicatedKeyException.class)
    public ShopAdminUser create(ShopAdminUser object) {
        //code to check for right email
        ShopAdminUser managerUser = managerUserDAO.create(object);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public ShopAdminUser get(Integer key) {
        ShopAdminUser managerUser = managerUserDAO.get(key);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public ShopAdminUser getByEmail(String email) {
        ShopAdminUser managerUser = managerUserDAO.getByEmail(email);
        return managerUser;
    }

    @Override
    @Transactional
    public ShopAdminUser update(ShopAdminUser object) {
        ShopAdminUser managerUser = managerUserDAO.update(object);
        return managerUser;
    }

    @Override
    @Transactional
    public boolean delete(ShopAdminUser object) {
        boolean res = managerUserDAO.delete(object);
        return res;
    }
}
