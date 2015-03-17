package com.kpi.education.rest.resources;

import com.kpi.education.businesslogic.Message;
import com.kpi.education.exceptions.DuplicatedKeyException;
import com.kpi.education.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
            return Response.ok(message1.getId()).status(200).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(403).build();
        }
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Message message = messageService.get(id);
        if (message != null)
            return Response.ok(message).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/received/byUserId/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReceivedMessages(@PathParam("userId") int userId,
                                        @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                        @DefaultValue("0") @QueryParam("maxResults") int maxResults) {
        List<Message> messages = messageService.getReceivedMessages(userId, firstResult, maxResults);
        if (messages.size() != 0)
            return Response.ok(messages).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSentMessages(@PathParam("userId") int userId,
                                    @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                    @DefaultValue("0") @QueryParam("maxResults") int maxResults) {
        List<Message> messages = messageService.getSentMessages(userId, firstResult, maxResults);
        if (messages.size() != 0)
            return Response.ok(messages).status(200).build();
        else
            return Response.status(404).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Message message) {
        Message message1 = messageService.update(message);
        if (message1 != null)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Message message) {
        boolean res = messageService.delete(message);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

}
