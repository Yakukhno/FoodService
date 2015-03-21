package com.foodservice.services;

import com.foodservice.businesslogic.data.SystemStatus;
import com.foodservice.dao.UserCommonDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommonService {

    private UserCommonDAO userCommonDAO;

    @Autowired
    public void setUserCommonDAO(UserCommonDAO userCommonDAO) {
        this.userCommonDAO = userCommonDAO;
    }

    public SystemStatus[] getSystemStatus(Integer[] identifiers) {
        return userCommonDAO.getSystemStatus(identifiers);
    }

    public boolean changeSystemStatus(Integer id, SystemStatus status) {
        return userCommonDAO.changeSystemStatus(id, status);
    }
}
