package com.kpi.education.web.rest.resources;

import com.kpi.education.businesslogic.friendship.Friendship;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/friendship")
public class FriendshipResource {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.manager = entityManagerFactory.createEntityManager();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Friendship friendship) {
        try {
            manager.getTransaction().begin();
            manager.persist(friendship);
            manager.getTransaction().commit();
            return Response.ok().status(200).build();
        } catch(HibernateException e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            return Response.ok().status(404).build();
        }
    }
}
