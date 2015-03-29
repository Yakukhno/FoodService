package com.foodservice.services;

import com.foodservice.businesslogic.data.SystemStatus;
import com.foodservice.businesslogic.data.UserType;
import com.foodservice.businesslogic.user.User;
import com.foodservice.dao.UserCommonDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getByEmail(String email) {
        return userCommonDAO.getByEmail(email);
    }

    public List<User> getMessageRelatedUsers(Integer id, int firstResult, int maxResults, UserType userType) {
        return userCommonDAO.getMessageRelatedUsers(id, firstResult, maxResults, userType);
    }
}
