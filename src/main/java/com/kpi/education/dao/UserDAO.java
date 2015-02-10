package com.kpi.education.dao;


import com.kpi.education.businesslogic.user.User;
import org.hibernate.HibernateException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by GR on 1/31/2015.
 */
public class UserDAO extends DAO {

    public UserDAO(EntityManagerFactory factory) {
        super(factory);
    }

    public List<User> byKeyword(String keyword) {
        List<User> result = null;
        try {
            TypedQuery<User> query = (TypedQuery<User>) getEntityManager().createQuery("from User u where u.firstName like :keyword or u.lastName like :keyword");
            query.setParameter("keyword", keyword);
            result = query.getResultList();
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

}
