package com.kpi.education.rest.resources;

import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.exceptions.DuplicatedKeyException;
import com.kpi.education.services.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/user/simple")
public class SimpleUserResource {
    
    private SimpleUserService simpleUserService;

    @Autowired
    public void setSimpleUserService(SimpleUserService simpleUserService) {
        this.simpleUserService = simpleUserService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(SimpleUser simpleUser) {
        try {
            SimpleUser managerUser1 = simpleUserService.create(simpleUser);
            return Response.ok(managerUser1).status(200).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(403).build();
        }
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("id") int id) {
        SimpleUser simpleUser = simpleUserService.get(id);
        if (simpleUser != null)
            return Response.ok(simpleUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("email") String email) {
        SimpleUser simpleUser = simpleUserService.getByEmain(email);
        if (simpleUser != null)
            return Response.ok(simpleUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(SimpleUser simpleUser) {
        SimpleUser simpleUser1 = simpleUserService.update(simpleUser);
        if (simpleUser1 != null)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(SimpleUser simpleUser) {
        boolean res = simpleUserService.delete(simpleUser);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }
    
}
