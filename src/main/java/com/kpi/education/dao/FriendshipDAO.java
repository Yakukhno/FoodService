package com.kpi.education.dao;

import com.kpi.education.businesslogic.friendship.Friendship;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendshipDAO implements CRUD<Friendship, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Friendship create(Friendship object) {
        return null;
    }

    @Override
    public Friendship retrieve(Integer object) {
        return null;
    }

    @Override
    public Friendship update(Friendship object) {
        return null;
    }

    @Override
    public boolean delete(Friendship object) {
        return false;
    }
}
