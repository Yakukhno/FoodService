package com.kpi.education.dao;

import com.kpi.education.businesslogic.friendship.Couple;
import com.kpi.education.businesslogic.friendship.Friendship;
import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.User;
import org.hibernate.HibernateException;

import javax.management.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Grigoriy on 2/1/2015.
 */
public class FriendshipDAO extends DAO<Friendship> {


    public FriendshipDAO(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public void create(Friendship object) {

    }

    @Override
    public Friendship retreive(Object object) {
        return null;
    }

    @Override
    public void update(Friendship object) {

    }

    @Override
    public void delete(Friendship object) {

    }

    public List<User> getUsers(User user, State state) {
        List<User> result = null;
        try {
//            TypedQuery<User> query1 = getEntityManager().createQuery("select f.couple.applicant from Friendship f where f.couple.acceptor = :user and f.state = :state", User.class);
//            TypedQuery<User> query2 = getEntityManager().createQuery("select f.couple.acceptor from Friendship f where f.couple.applicant = :user and f.state = :state", User.class);
            TypedQuery<User> query1 = (TypedQuery<User>) getEntityManager().createNamedQuery("user.list");
            TypedQuery<User> query2 = (TypedQuery<User>) getEntityManager().createNamedQuery("user.reverse.list");
            query1.setParameter("user", user);
            query2.setParameter("user", user);
            query1.setParameter("state", state);
            query2.setParameter("state", state);
            result = query1.getResultList();
            result.addAll(query2.getResultList());

//            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
//            criteriaQuery.from(Friendship.class).
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void sendRequest(User applicant, User acceptor) {
        try {
            getEntityManager().getTransaction().begin();
            Friendship friendship = new Friendship();
            friendship.setCouple(new Couple(applicant, acceptor));
            friendship.setState(State.NEW);
            getEntityManager().merge(friendship);
            getEntityManager().getTransaction().commit();
        } catch(HibernateException e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void decideOnRequest(User applicant, User acceptor, State state) {
        try {
            getEntityManager().getTransaction().begin();
            Friendship friendship = getEntityManager().find(Friendship.class, new Couple(applicant, acceptor));
            friendship.setState(state);
            getEntityManager().merge(friendship);
            getEntityManager().getTransaction().commit();
        } catch(HibernateException e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
