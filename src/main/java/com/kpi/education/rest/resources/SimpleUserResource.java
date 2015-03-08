package com.kpi.education.rest.resources;

import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.dao.SimpleUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/user/simple")
public class SimpleUserResource {
    
    private SimpleUserDAO simpleUserDAO;

    @Autowired
    public void setSimpleUserDAO(SimpleUserDAO simpleUserDAO) {
        this.simpleUserDAO = simpleUserDAO;
    }
    

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(SimpleUser simpleUser) {
        SimpleUser simpleUser1 = simpleUserDAO.create(simpleUser);
        if (simpleUser1 != null)
            return Response.ok(simpleUser1).status(200).build();
        else 
            return Response.status(403).build();
    }

    @GET
    @Path("/byid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("id") int id) {
        SimpleUser simpleUser = simpleUserDAO.retrieve(id);
        if (simpleUser != null)
            return Response.ok(simpleUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/byLogin/{login}")
    public Response retrieve(@PathParam("login") String login) {
        SimpleUser simpleUser = simpleUserDAO.retrieveByLogin(login);
        if (simpleUser != null)
            return Response.ok(simpleUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(SimpleUser simpleUser) {
        SimpleUser simpleUser1 = simpleUserDAO.update(simpleUser);
        if (simpleUser1 != null)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(SimpleUser simpleUser) {
        boolean res = simpleUserDAO.delete(simpleUser);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }
    
}
