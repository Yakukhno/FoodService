package com.foodservice.services;

import com.foodservice.businesslogic.data.SystemStatus;
import com.foodservice.businesslogic.data.UserType;
import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.dao.ShopAdminUserDAO;
import com.foodservice.exceptions.DuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ShopAdminUserService implements UserService<ShopAdminUser> {


    private ShopAdminUserDAO managerUserDAO;

    @Autowired
    public void setManagerUserDAO(ShopAdminUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }


    @Override
    public ShopAdminUser create(ShopAdminUser object) {
        //code to check for right email
        object.setSystemStatus(SystemStatus.OFFLINE);
        object.setUserType(UserType.SHOP_ADMIN);
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
    public ShopAdminUser update(ShopAdminUser object) {
        ShopAdminUser managerUser = managerUserDAO.update(object);
        return managerUser;
    }

    @Override
    public boolean delete(ShopAdminUser object) {
        boolean res = managerUserDAO.delete(object);
        return res;
    }
}
