package com.foodservice.rest.resources;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.friendship.Friendship;
import com.foodservice.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/friendship")
public class FriendshipResource {

    private FriendshipService friendshipService;

    @Autowired
    public void setFriendshipService(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @POST
    public Response createRelations(@QueryParam("applicantId") int applicantId,
                                    @QueryParam("acceptorId") int acceptorId,
                                    @QueryParam("state") State state) {
        Friendship friendship = friendshipService.createRelations(applicantId, acceptorId, state);
        if (friendship != null) {
            return Response.ok(friendship.getId()).status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/byCouple")
    public Response changeState(@QueryParam("applicantId") int applicantId,
                                @QueryParam("acceptorId") int acceptorId,
                                @QueryParam("state") State state) {
        boolean res = friendshipService.changeState(applicantId, acceptorId, state);
        if (res == true) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/byId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeState(@QueryParam("id") int id,
                                @QueryParam("state") State state) {
        boolean res = friendshipService.changeState(id, state);
        if (res == true) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer object) {
        Friendship friendship = friendshipService.get(object);
        if (friendship != null) {
            return Response.ok(friendship.getId()).status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Friendship object) {
        Friendship friendship = friendshipService.update(object);
        if (friendship != null) {
            return Response.ok(friendship.getId()).status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Friendship object) {
        boolean res = friendshipService.delete(object);
        if (res == true) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
