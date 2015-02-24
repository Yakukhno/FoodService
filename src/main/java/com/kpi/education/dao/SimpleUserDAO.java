package com.kpi.education.dao;

import com.kpi.education.businesslogic.user.SimpleUser;

import javax.persistence.EntityManagerFactory;

public class SimpleUserDAO extends DAO<SimpleUser, Integer> {

    public SimpleUserDAO(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public SimpleUser create(SimpleUser simpleUser) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(simpleUser);
            getEntityManager().getTransaction().commit();
            return simpleUser;
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public SimpleUser retrieve(Integer id) {
        try {
            SimpleUser simpleUser = getEntityManager().find(SimpleUser.class, id);
            return simpleUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SimpleUser update(SimpleUser simpleUser) {
        try {
            getEntityManager().getTransaction().begin();
            simpleUser = getEntityManager().merge(simpleUser);
            getEntityManager().getTransaction().commit();
            return simpleUser;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(SimpleUser simpleUser) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(simpleUser);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
