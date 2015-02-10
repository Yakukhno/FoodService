package com.kpi.education;

import com.kpi.education.businesslogic.data.State;
import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.businesslogic.user.User;
import com.kpi.education.dao.FriendshipDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import  static org.junit.Assert.*;

public class Tester {

    public static EntityManagerFactory factory;
    public static EntityManager manager;
    public static AbstractApplicationContext ctx;

    @BeforeClass
    public static void beforeTests() {
//        factory = Persistence.createEntityManagerFactory("unit1");
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx.registerShutdownHook();
        factory = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
        manager = factory.createEntityManager();
    }

    @AfterClass
    public static void afterClass() {
        manager.close();
        factory.close();
    }

    @Test
    public void testFriendship() {
        FriendshipDAO friendshipDAO = (FriendshipDAO) ctx.getBean("FriendshipDAO");

        SimpleUser u1 = new SimpleUser();
        u1.setFirstName("User1");

        SimpleUser u2 = new SimpleUser();
        u2.setFirstName("User2");

        SimpleUser u3 = new SimpleUser();
        u3.setFirstName("User3");

        manager.getTransaction().begin();
        manager.persist(u1);
        manager.persist(u2);
        manager.persist(u3);
        manager.getTransaction().commit();

        friendshipDAO.sendRequest(u1, u2);
        friendshipDAO.sendRequest(u1, u3);
        friendshipDAO.sendRequest(u2, u3);

        friendshipDAO.decideOnRequest(u1, u2, State.ACCEPTED);
        friendshipDAO.decideOnRequest(u1, u3, State.ACCEPTED);
        friendshipDAO.decideOnRequest(u2, u3, State.REFUSED);

        List<User> u1friends = friendshipDAO.getUsers(u1, State.ACCEPTED);
        assertTrue(u1friends.size() == 2);
        assertTrue(u1friends.contains(u2));
        assertTrue(u1friends.contains(u3));

        List<User> u2friends = friendshipDAO.getUsers(u2, State.ACCEPTED);
        assertTrue(u2friends.size() == 1);
        assertTrue(u2friends.contains(u1));

        List<User> u3friends = friendshipDAO.getUsers(u3, State.ACCEPTED);
        assertTrue(u3friends.size() == 1);
        assertTrue(u3friends.contains(u1));

        List<User> u3followers = friendshipDAO.getUsers(u3, State.REFUSED);
        assertTrue(u3followers.size() == 1);
        assertTrue(u3followers.contains(u2));
    }
}
