package com.foodservice.services;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.friendship.Friendship;
import com.foodservice.businesslogic.user.SimpleUser;
import com.foodservice.dao.FriendshipDAO;
import com.foodservice.dao.SimpleUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class FriendshipService {

    private FriendshipDAO friendshipDAO;
    private SimpleUserDAO simpleUserDAO;

    @Autowired
    public void setFriendshipDAO(FriendshipDAO friendshipDAO) {
        this.friendshipDAO = friendshipDAO;
    }
    @Autowired
    public void setSimpleUserDAO(SimpleUserDAO simpleUserDAO) {
        this.simpleUserDAO = simpleUserDAO;
    }

    public Friendship createRelations(int applicantId, int acceptorId, State state) {
        SimpleUser applicant = simpleUserDAO.get(applicantId);
        SimpleUser acceptor  = simpleUserDAO.get(acceptorId);
        return friendshipDAO.create(new Friendship(applicant, acceptor, state));
    }

    public boolean changeState(int applicantId, int acceptorId, State state) {
        return friendshipDAO.changeState(applicantId, acceptorId, state);
    }

    public boolean changeState(int id, State state) {
        return friendshipDAO.changeState(id, state);
    }

    @Transactional(readOnly = true)
    public Friendship get(Integer object) {
        return friendshipDAO.get(object);
    }

    public Friendship update(Friendship object) {
        return friendshipDAO.update(object);
    }

    public boolean delete(Friendship object) {
        return friendshipDAO.delete(object);
    }

}
