package com.kpi.education.dao;

import com.kpi.education.businesslogic.user.SimpleUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleUserDAO implements CRUD <SimpleUser, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public SimpleUser create(SimpleUser object) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.persist(object);
            tx.commit();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public SimpleUser retrieve(Integer object) {
        Session session = sessionFactory.openSession();
        try {
            SimpleUser simpleUser = (SimpleUser) session.get(SimpleUser.class, object);
            return simpleUser;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    public SimpleUser retrieveByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query query = session.createQuery("from SimpleUser where login = :login");
            query.setParameter("login", login);
            tx.commit();
            return (SimpleUser) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public SimpleUser update(SimpleUser object) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(object);
            tx.commit();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(SimpleUser object) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query query = session.createQuery("delete SimpleUser where id = :id");
            query.setParameter("id", object.getId());
            int res = query.executeUpdate();
            tx.commit();
            return res == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return false;
        }
    }
    
}
