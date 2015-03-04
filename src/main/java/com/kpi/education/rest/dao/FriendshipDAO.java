package com.kpi.education.rest.dao;

import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.friendship.Friendship;
import com.kpi.education.businesslogic.user.User;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "friendshipDAO")
public class FriendshipDAO extends DAO<Friendship, Integer> {

    @Autowired
    public FriendshipDAO(EntityManagerFactory factory) {
        super(factory);
    }

    public List<User> getUsers(User user, State state) {
        List<User> result = null;
        try {
            TypedQuery<User> query1 = (TypedQuery<User>) getEntityManager().createNamedQuery("user.list");
            TypedQuery<User> query2 = (TypedQuery<User>) getEntityManager().createNamedQuery("user.reverse.list");
            query1.setParameter("user", user);
            query2.setParameter("user", user);
            query1.setParameter("state", state);
            query2.setParameter("state", state);
            result = query1.getResultList();
            result.addAll(query2.getResultList());
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Friendship create(Friendship friendship) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(friendship);
            getEntityManager().getTransaction().commit();
            return friendship;
        } catch(HibernateException e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Friendship retrieve(Integer id) {
        try {
            Friendship friendship = getEntityManager().find(Friendship.class, id);
            return friendship;
        } catch(HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Friendship update(Friendship friendship) {
        try {
            getEntityManager().getTransaction().begin();
            Friendship friendship1 = getEntityManager().merge(friendship);
            getEntityManager().getTransaction().commit();
            return friendship1;
        } catch(HibernateException e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Friendship friendship) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(friendship);
            getEntityManager().getTransaction().commit();
            return true;
        } catch(HibernateException e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
