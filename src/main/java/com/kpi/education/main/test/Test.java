package com.kpi.education.main.test;

import com.kpi.education.businesslogic.Message;
import com.kpi.education.businesslogic.Photo;
import com.kpi.education.businesslogic.user.SimpleUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.*;

/**
 * Created by Grigoriy on 1/26/2015.
 */
public class Test {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        session.beginTransaction();

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


        session.save(u1);
//        session.save(u2);
//        session.save(u3);
        session.save(m1);
        session.save(m2);

        session.getTransaction().commit();
        session.close();
        factory.close();

    }
}
