package com.kpi.education.dao;

import com.kpi.education.businesslogic.friendship.Couple;
import com.kpi.education.businesslogic.friendship.Friendship;
import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.User;
import org.springframework.beans.factory.DisposableBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Grigoriy on 2/1/2015.
 */
public class FriendshipDAO extends DAO {


    public FriendshipDAO(EntityManagerFactory factory) {
        super(factory);
    }

    public List<User> getUsers(User user, State state) {
        List<User> result = null;
        TypedQuery<User> query1 = getEntityManager().createQuery("select c.couple.applicant from Friendship c where c.couple.acceptor = :user and c.state = :state", User.class);
        TypedQuery<User> query2 = getEntityManager().createQuery("select f.couple.acceptor from Friendship f where f.couple.applicant = :user and f.state = :state", User.class);
        query1.setParameter("user", user);
        query2.setParameter("user", user);
        query1.setParameter("state", state);
        query2.setParameter("state", state);
        result = query1.getResultList();
        result.addAll(query2.getResultList());
        return result;
    }

    public void sendRequest(User applicant, User acceptor) {
        getEntityManager().getTransaction().begin();
        Friendship friendship = new Friendship();
        friendship.setCouple(new Couple(applicant, acceptor));
        friendship.setState(State.NEW);
        getEntityManager().merge(friendship);
        getEntityManager().getTransaction().commit();
    }

    public void decideOnRequest(User applicant, User acceptor, State state) {
        getEntityManager().getTransaction().begin();
        Friendship friendship = getEntityManager().find(Friendship.class, new Couple(applicant, acceptor));
        friendship.setState(state);
        System.out.println("//////////////////////////////////////////////////// NULL " + friendship);
        getEntityManager().merge(friendship);
//        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

}
