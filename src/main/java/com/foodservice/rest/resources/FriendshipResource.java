package com.foodservice.rest.resources;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.friendship.Friendship;
import com.foodservice.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Component
@Path("/friendship")
public class FriendshipResource {

    private FriendshipService friendshipService;

    @Autowired
    public void setFriendshipService(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

//    public Response createRelations(@QueryParam("applicantId") int applicantId,
//                                    @QueryParam("acceptorId") int acceptorId,
//                                    @QueryParam("state") State state) {
//        Friendship friendship = friendshipService.createRelations(applicantId, acceptorId, state);
//    }
//
//    public Response changeState(@QueryParam("applicantId") int applicantId,
//                                @QueryParam("acceptorId") int acceptorId,
//                                @QueryParam("state") State state) {
//    }
//
//    public Response changeState(@QueryParam("id") int id,
//                                @QueryParam("state") State state) {
//    }
//
//    public Response get(Integer object) {
//    }
//
//    public Response update(Friendship object) {
//    }
//
//    public Response delete(Friendship object) {
//
//    }
}
