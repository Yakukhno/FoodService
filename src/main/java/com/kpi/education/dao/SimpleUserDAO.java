package com.kpi.education.dao;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Grigoriy on 2/4/2015.
 */
public class SimpleUserDAO extends UserDAO {

    public SimpleUserDAO(EntityManagerFactory factory) {
        super(factory);
    }

    public void sendOrdering() {

    }
}
