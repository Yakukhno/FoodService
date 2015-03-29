package com.foodservice.services;

import com.foodservice.businesslogic.Message;
import com.foodservice.dao.MessageDAO;
import com.foodservice.exceptions.DuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class MessageService {

    private MessageDAO messageDAO;

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message create(Message object) {
        return messageDAO.create(object);
    }

    @Transactional(readOnly = true)
    public Message get(Integer object) {
        return messageDAO.get(object);
    }

//    @Transactional(readOnly = true)
//    public List<Message> getReceivedMessages(int userId, int firstResult, int maxResults) {
//        return messageDAO.getReceivedMessages(userId, firstResult, maxResults);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Message> getSentMessages(int userId, int firstResult, int maxResults) {
//        return messageDAO.getSentMessages(userId, firstResult, maxResults);
//    }

    @Transactional(readOnly = true)
    public List<Message> getDialogMessages(int user1Id, int user2Id, int firstResult, int maxResults) {
        return messageDAO.getDialogMessages(user1Id, user2Id, firstResult, maxResults);
    }

    public Message update(Message object) {
        return messageDAO.update(object);
    }

    public boolean delete(Message object) {
        return messageDAO.delete(object);
    }
}
