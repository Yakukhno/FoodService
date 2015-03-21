package com.foodservice.dao;

import com.foodservice.businesslogic.friendship.Friendship;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
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
    public Friendship get(Integer object) {
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
