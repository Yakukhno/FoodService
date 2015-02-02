package com.kpi.education.dao;

import com.kpi.education.businesslogic.Friendship;
import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.User;
import org.hibernate.HibernateException;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Grigoriy on 2/1/2015.
 */
public class FriendshipDAO {

    private EntityManager manager;

    public List<User> getUsers(User user, State state) {
        EntityTransaction t = null;
        List<User> result = null;
        try {
            t = manager.getTransaction();
            TypedQuery<User> query1 = manager.createNamedQuery("getUsers1", User.class);
            TypedQuery<User> query2 = manager.createNamedQuery("getUsers2", User.class);
            query1.setParameter("user", user);
            query2.setParameter("user", user);
            query1.setParameter("state", state);
            query2.setParameter("state", state);

            result = query1.getResultList();
            result.addAll(query2.getResultList());
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
        }
        return result;
    }

//    public
}
