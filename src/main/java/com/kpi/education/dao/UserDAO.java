package com.kpi.education.dao;

import com.kpi.education.businesslogic.Message;
import com.kpi.education.businesslogic.user.User;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR on 1/31/2015.
 */
public abstract class UserDAO {

    private EntityManager manager;

//    public boolean acceptFriendship(User applicant) {
//        manager.getTransaction().begin();
//            Query query = manager.
//        manager.getTransaction().commit();
//        manager.clear();
//    }
}
