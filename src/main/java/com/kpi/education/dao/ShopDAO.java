package com.kpi.education.dao;

import com.kpi.education.businesslogic.Shop;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.hibernate.Query;
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
        Session session = sessionFactory.getCurrentSession();
        Shop shop = (Shop) session.get(Shop.class, object);
        return shop;
    }

    @Override
    @Transactional(readOnly = true)
    public Shop update(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    @Transactional
    public boolean delete(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Shop where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
}
