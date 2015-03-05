package com.kpi.education.rest.dao;

import com.kpi.education.businesslogic.user.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "simpleUserDAO")
public class SimpleUserDAO extends DAO<SimpleUser, Integer> {

    @Autowired
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

    public SimpleUser retrieveByLogin(String login) {
        try {
            TypedQuery<SimpleUser> query = (TypedQuery<SimpleUser>) getEntityManager().createNamedQuery("simpleUser.byLogin");
            query.setParameter("login", login);
            List<SimpleUser> users = query.getResultList();
            //checking on errors in database
            if (users.size() > 1)
                throw new IllegalStateException("There are more than one users with the same 'login'!");
            if (users.size() == 0)
                return null;
            return users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SimpleUser retrieveByLoginAndPassword(String login, String password) {
        try {
            TypedQuery<SimpleUser> query = (TypedQuery<SimpleUser>) getEntityManager().createNamedQuery("simpleUser.byLoginAndPassword");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<SimpleUser> users = query.getResultList();
            //checking on errors in database
            if (users.size() > 1)
                throw new IllegalStateException("There are more than one users with the same 'login'!");
            return users.get(0);
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
