package com.foodservice.dao;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.friendship.Friendship;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.businesslogic.user.SimpleUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class SimpleUserDAO implements UserDAO<SimpleUser, Integer> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(noRollbackFor =  DuplicatedKeyException.class)
    public SimpleUser create(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        if (getByEmail(object.getEmail()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        SimpleUser managerUser = (SimpleUser) session.get(SimpleUser.class, object);
        return managerUser;
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SimpleUser where email = :email");
        query.setParameter("email", email);
        return (SimpleUser) query.uniqueResult();
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getRelativeUsersOf(int id, State state) {
        Session session = sessionFactory.getCurrentSession();
//        Criteria criteria1 = session.createCriteria(Friendship.class, "f");
//        criteria1.setProjection(Property.forName("applicant").as("apl"))
//                .add(Restrictions.eq("apl.acceptor.id", id));
//
//        Criteria criteria2 = session.createCriteria(Friendship.class, "f");
//        criteria1.setProjection(Property.forName("acceptor").as("ac"))
//                .add(Restrictions.eq("ac.applicant.id", id));
        Query query1 = session.createQuery("select f.applicant from Friendship f where f.acceptor = :user and f.state = :state");
        Query query2 = session.createQuery("select f.acceptor from Friendship f where f.applicant = :user and f.state = :state");
        List<SimpleUser> users = query1.list();
        users.addAll(query2.list());
        return users;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public SimpleUser update(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    public boolean delete(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete SimpleUser where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }
    
}
