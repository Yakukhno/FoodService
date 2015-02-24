package com.kpi.education.dao;

import com.kpi.education.businesslogic.user.ManagerUser;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Grigoriy on 2/23/2015.
 */
public class ManagerUserDAO extends DAO<ManagerUser, Integer> {
    
    public ManagerUserDAO(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public ManagerUser create(ManagerUser managerUser) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(managerUser);
            getEntityManager().getTransaction().commit();
            return managerUser;
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public ManagerUser retrieve(Integer id) {
        try {
            ManagerUser managerUser = getEntityManager().find(ManagerUser.class, id);
            return managerUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ManagerUser update(ManagerUser managerUser) {
        try {
            getEntityManager().getTransaction().begin();
            managerUser = getEntityManager().merge(managerUser);
            getEntityManager().getTransaction().commit();
            return managerUser;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(ManagerUser managerUser) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(managerUser);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}

