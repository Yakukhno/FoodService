package com.kpi.education.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class DAO<T,V> implements DisposableBean {

    private EntityManager manager;

    @Autowired
    public DAO(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    public EntityManager getEntityManager() {return manager;}

    public abstract T create(T object);
    public abstract T retrieve(V object);
    public abstract T update(T object);
    public abstract boolean delete(T object);


    @Override
    public void destroy() throws Exception {
        manager.close();
    }
}
