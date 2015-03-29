package com.foodservice.rest.resources;

import com.foodservice.businesslogic.Message;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/massages")
public class MassageResource {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Message message) {
        try {
            Message message1 = messageService.create(message);
            return Response.ok(message1.getId()).status(Response.Status.CREATED).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Message message = messageService.get(id);
        if (message != null)
            return Response.ok(message).status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/dialog")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDialogMessages(@PathParam("user1Id") int user1Id,
                                      @PathParam("user2Id") int user2Id,
                                    @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                    @DefaultValue("1000000") @QueryParam("maxResults") int maxResults) {
        List<Message> messages = messageService.getDialogMessages(user1Id, user2Id, firstResult, maxResults);
        if (messages.size() != 0)
            return Response.ok(messages).status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Message message) {
        Message message1 = messageService.update(message);
        if (message1 != null)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Message message) {
        boolean res = messageService.delete(message);
        if (res)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }

}
