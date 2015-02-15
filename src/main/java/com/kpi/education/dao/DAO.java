package com.kpi.education.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public abstract class DAO<T> implements DisposableBean {

    private EntityManager manager;

    @Autowired
    public DAO(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    public EntityManager getEntityManager() {return manager;}

    public abstract void create(T object);
    public abstract T retreive(Object object);
    public abstract void update(T object);
    public abstract void delete(T object);


    @Override
    public void destroy() throws Exception {
        manager.close();
    }
}
