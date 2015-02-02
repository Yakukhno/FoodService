package com.kpi.education;

import com.kpi.education.businesslogic.Message;
import com.kpi.education.businesslogic.user.SimpleUser;
import lombok.Setter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Tester {

    public static EntityManagerFactory factory;
    public static EntityManager manager;

    @BeforeClass
    public static void beforeTests() {
//        factory = Persistence.createEntityManagerFactory("unit1");
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        factory = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
        manager = factory.createEntityManager();
    }

    @AfterClass
    public static void afterClass() {
        manager.close();
        factory.close();
    }

    @Test
    public void testSimpleUser() {
        manager.getTransaction().begin();

        SimpleUser u1 = new SimpleUser();
        u1.setFirstName("User1");

        SimpleUser u2 = new SimpleUser();
        u2.setFirstName("User2");

        SimpleUser u3 = new SimpleUser();
        u3.setFirstName("User3");

        Message m1 = new Message();
        m1.setText("message to user2 user3");
        m1.getReceivers().add(u2);
        m1.getReceivers().add(u3);

        Message m2 = new Message();
        m2.setText("message to user3");
        m2.getReceivers().add(u3);

        u1.getSentMessages().add(m1);
        u2.getSentMessages().add(m2);

        manager.persist(u1);
        manager.persist(u2);
        manager.persist(u3);
        manager.persist(m1);
        manager.persist(m2);

        manager.getTransaction().commit();
        manager.clear();

        manager.getTransaction().begin();
        SimpleUser u = manager.find(SimpleUser.class, u3.getId());
        List<Message> messages = u.getReceivedMessages();
        System.out.println("//////////////////////// size: " + messages.size());
        System.out.println("//////////////////////// messages to user3");
        for (Message m : messages)
            System.out.println("///////////////////////// " + m);
        manager.getTransaction().commit();
    }
}
