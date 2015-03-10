package com.kpi.education.dao;

import com.kpi.education.businesslogic.user.ManagerUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ManagerUserDAO implements CRUD<ManagerUser, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public ManagerUser create(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object;
    }

    @Override
    public ManagerUser retrieve(Integer object) {
        Session session = sessionFactory.openSession();
        try {
            ManagerUser managerUser = (ManagerUser) session.get(ManagerUser.class, object);
            return managerUser;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    public ManagerUser retrieveByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query query = session.createQuery("from ManagerUser where login = :login");
            query.setParameter("login", login);
            tx.commit();
            return (ManagerUser) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return null;
        }
    }
    @Override
    public ManagerUser update(ManagerUser object) {
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
    public boolean delete(ManagerUser object) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query query = session.createQuery("delete ManagerUser where id = :id");
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
