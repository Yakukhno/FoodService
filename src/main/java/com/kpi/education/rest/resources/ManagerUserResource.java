package com.kpi.education.rest.resources;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.dao.ManagerUserDAO;
import com.kpi.education.exceptions.DuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/user/manager")
public class ManagerUserResource {

    private ManagerUserDAO managerUserDAO;

    @Autowired
    public void setManagerUserDAO(ManagerUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ManagerUser managerUser) {
        try {
            ManagerUser managerUser1 = managerUserDAO.create(managerUser);
            return Response.ok(managerUser1).status(200).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(403).build();
        }
    }

    @GET
    @Path("/byid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("id") int id) {
        ManagerUser managerUser = managerUserDAO.retrieve(id);
        if (managerUser != null)
            return Response.ok(managerUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/byLogin/{login}")
    public Response retrieve(@PathParam("login") String login) {
        ManagerUser managerUser = managerUserDAO.retrieveByLogin(login);
        if (managerUser != null)
            return Response.ok(managerUser).status(200).build();
        else
            return Response.status(404).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ManagerUser managerUser) {
        ManagerUser managerUser1 = managerUserDAO.update(managerUser);
        if (managerUser1 != null)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ManagerUser managerUser) {
        boolean res = managerUserDAO.delete(managerUser);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }
}
