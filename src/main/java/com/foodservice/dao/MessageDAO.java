package com.foodservice.dao;

import com.foodservice.businesslogic.Message;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
        session.persist(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public Message get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Message message = (Message) session.get(Message.class, object);
        return message;
    }

    @Transactional(readOnly = true)
    public List<Message> getDialogMessages(int user1Id, int user2Id, int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Message.class, "m");
        criteria.add(Restrictions.or(
                Restrictions.and(
                    Restrictions.eq("m.senderId", user1Id), Restrictions.eq("m.receiverId", user2Id)),
                Restrictions.and(
                    Restrictions.eq("m.senderId", user2Id), Restrictions.eq("m.receiverId", user1Id))));
        criteria.addOrder(Order.desc("m.time"));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
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
        Query query = session.createQuery("delete Message m where m.id = :id");
        query.setParameter("id", object.getId());
        int res = query.executeUpdate();
        return res == 1;
    }



}
