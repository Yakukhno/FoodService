package com.kpi.education.services;

import com.kpi.education.businesslogic.Message;
import com.kpi.education.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private MessageDAO messageDAO;

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Transactional
    public Message create(Message object) {
        return messageDAO.create(object);
    }

    @Transactional(readOnly = true)
    public Message get(Integer object) {
        return messageDAO.get(object);
    }

    @Transactional(readOnly = true)
    public List<Message> getReceivedMessages(int userId, int firstResult, int maxResults) {
        return messageDAO.getReceivedMessages(userId, firstResult, maxResults);
    }

    @Transactional(readOnly = true)
    public List<Message> getSentMessages(int userId, int firstResult, int maxResults) {
        return messageDAO.getSentMessages(userId, firstResult, maxResults);
    }

    @Transactional
    public Message update(Message object) {
        return messageDAO.update(object);
    }

    @Transactional
    public boolean delete(Message object) {
        return messageDAO.delete(object);
    }
}
