package com.kpi.education.web.rest.resources;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.businesslogic.user.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/user/manager")
public class ManagerUserResource {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.manager = entityManagerFactory.createEntityManager();
    }

    @POST
    @Path("/create/form")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ManagerUser managerUser) {
        System.out.println(managerUser);
        try {
            manager.getTransaction().begin();
            manager.persist(managerUser);
            manager.getTransaction().commit();
            return Response.ok(managerUser).status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/retrieve/byid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("id") int id) {
        try {
            SimpleUser simpleUser = manager.find(SimpleUser.class, id);
            return Response.ok(simpleUser).status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(SimpleUser simpleUser) {
        try {
            manager.getTransaction().begin();
            simpleUser = manager.merge(simpleUser);
            manager.getTransaction().commit();
            return Response.ok(simpleUser).status(200).build();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(SimpleUser simpleUser) {
        try {
            manager.getTransaction().begin();
            manager.remove(simpleUser);
            manager.getTransaction().commit();
            return Response.noContent().status(200).build();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(404).build();
        }
    }
}
