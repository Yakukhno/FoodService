package com.kpi.education.dao;

import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SimpleUserDAO implements CRUD<SimpleUser, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor =  DuplicatedKeyException.class)
    public SimpleUser create(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        if (getByEmail(object.getEmail()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public SimpleUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        SimpleUser managerUser = (SimpleUser) session.get(SimpleUser.class, object);
        return managerUser;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public SimpleUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SimpleUser where email = :email");
        query.setParameter("email", email);
        return (SimpleUser) query.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SimpleUser update(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete SimpleUser where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
    
}
