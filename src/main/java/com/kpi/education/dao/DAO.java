package com.kpi.education.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by GR on 1/31/2015.
 */
public abstract class DAO {

    private EntityManager manager;

    public DAO(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return manager;
    }

    public EntityTransaction getTransaction() {
        return manager.getTransaction();
    }
}
