package com.kpi.education.dao;

import com.kpi.education.businesslogic.Shop;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ShopDAO implements CRUD<Shop, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Shop create(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        if (get(object.getId()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public Shop get(Integer object) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Shop update(Shop object) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Shop object) {
        return false;
    }
}
