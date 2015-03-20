package com.foodservice.dao;

import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.exceptions.DuplicatedKeyException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ShopAdminUserDAO implements UserDAO<ShopAdminUser, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor =  DuplicatedKeyException.class)
    public ShopAdminUser create(ShopAdminUser object) {
        Session session = sessionFactory.getCurrentSession();
        if (getByEmail(object.getEmail()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ShopAdminUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        ShopAdminUser managerUser = (ShopAdminUser) session.get(ShopAdminUser.class, object);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ShopAdminUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ShopAdminUser where email = :email");
        query.setParameter("email", email);
        return (ShopAdminUser) query.uniqueResult();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ShopAdminUser update(ShopAdminUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ShopAdminUser object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete ShopAdminUser where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
}
