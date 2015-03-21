package com.foodservice.dao;

import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.businesslogic.Message;
import com.foodservice.businesslogic.Shop;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class MessageDAO implements CRUD<Message, Integer>{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Message create(Message object) {
        Session session = sessionFactory.getCurrentSession();
        if (get(object.getId()) != null) throw new DuplicatedKeyException();
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public Message get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Message message = (Message) session.get(Shop.class, object);
        return message;
    }

    @Transactional(readOnly = true)
    public List<Message> getReceivedMessages(int userId, int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        List<Message> messages = session.createQuery("receivedMessages from User").list();
        return messages;
    }

    @Transactional(readOnly = true)
    public List<Message> getSentMessages(int userId, int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        List<Message> messages = session.createQuery("sentMessages from User").list();
        return messages;
    }

    @Override
    public Message update(Message object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
        return object;
    }

    @Override
    public boolean delete(Message object) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Message where id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }



}
