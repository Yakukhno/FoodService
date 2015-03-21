package com.foodservice.dao;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.friendship.Friendship;
import com.foodservice.exceptions.DuplicatedKeyException;
import org.hibernate.Query;
import org.hibernate.Session;
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
        Session session = sessionFactory.getCurrentSession();
        if (get(object.getId()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public Friendship get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Friendship friendship = (Friendship) session.get(Friendship.class, object);
        return friendship;
    }

    public boolean changeState(int applicantId, int acceptorId, State state) {
        Session session = sessionFactory.getCurrentSession();
        int res = session.createQuery("update Friendship set state = :state " +
                "where applicant.id = :applicantId and " +
                "acceptor.id = :acceptorId")
                .setParameter("applicantId", applicantId)
                .setParameter("acceptorId", acceptorId).executeUpdate();
        return res == 1;
    }

    public boolean changeState(int id, State state) {
        Session session = sessionFactory.getCurrentSession();
        int res = session.createQuery("update Friendship set state = :state " +
                "where id = :id")
                .setParameter("id", id).executeUpdate();
        return res == 1;
    }

    @Override
    public Friendship update(Friendship object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    public boolean delete(Friendship object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Friendship where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
}
