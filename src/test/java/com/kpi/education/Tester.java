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
        u1.setFirstName("First User");

        SimpleUser u2 = new SimpleUser();
        u2.setFirstName("Second User");

        SimpleUser u3 = new SimpleUser();
        u3.setFirstName("Third User");

        u1.getFriends().add(u2);
        u1.getFriends().add(u3);

        u2.getFriends().add(u1);
        u2.getFriends().add(u3);

        u3.getFriends().add(u1);
        u3.getFriends().add(u2);

        Message m1 = new Message();
        m1.setText("first message");
        m1.getReceivers().add(u2);
        m1.getReceivers().add(u3);

        Message m2 = new Message();
        m2.setText("second message");
        m2.getReceivers().add(u3);

        u1.getSentMessages().add(m1);
        u1.getSentMessages().add(m2);

        manager.persist(u1);
        manager.persist(m1);
        manager.persist(m2);

        manager.getTransaction().commit();
    }
}
