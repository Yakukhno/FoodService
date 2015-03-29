package com.foodservice.services;

import com.foodservice.businesslogic.data.SystemStatus;
import com.foodservice.businesslogic.data.UserType;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.businesslogic.user.SimpleUser;
import com.foodservice.dao.SimpleUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class SimpleUserService implements UserService<SimpleUser> {
    
    private SimpleUserDAO simpleUserDAO;

    @Autowired
    public void setSimpleUserDAO(SimpleUserDAO simpleUserDAO) {
        this.simpleUserDAO = simpleUserDAO;
    }

    @Override
    public SimpleUser create(SimpleUser object) {

        object.setSystemStatus(SystemStatus.OFFLINE);
        object.setUserType(UserType.SIMPLE);
        SimpleUser simpleUser = simpleUserDAO.create(object);
        return simpleUser;
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser get(Integer key) {
        SimpleUser simpleUser = simpleUserDAO.get(key);
        return simpleUser;
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser getByEmail(String email) {
        SimpleUser simpleUser = simpleUserDAO.getByEmail(email);
        return simpleUser;
    }

    @Override
    public SimpleUser update(SimpleUser object) {
        SimpleUser simpleUser = simpleUserDAO.update(object);
        return simpleUser;
    }

    @Override
    public boolean delete(SimpleUser object) {
        boolean res = simpleUserDAO.delete(object);
        return res;
    }
}
