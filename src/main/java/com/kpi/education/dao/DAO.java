package com.kpi.education.dao;

import org.springframework.beans.factory.DisposableBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public abstract class DAO implements DisposableBean {

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

    @Override
    public void destroy() throws Exception {
        manager.close();
    }
}
