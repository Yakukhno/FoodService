package com.foodservice.rest.resources;

import com.foodservice.businesslogic.user.ShopAdminUser;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.services.ShopAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/users/admin")
public class ShopAdminUserResource {

    private ShopAdminUserService shopAdminUserService;

    @Autowired
    public void setShopAdminUserService(ShopAdminUserService shopAdminUserService) {
        this.shopAdminUserService = shopAdminUserService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ShopAdminUser managerUser) {
        try {
            ShopAdminUser managerUser1 = shopAdminUserService.create(managerUser);
            return Response.ok(managerUser1).status(200).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(403).build();
        }
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        ShopAdminUser managerUser = shopAdminUserService.get(id);
        if (managerUser != null)
            return Response.ok(managerUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("email") String email) {
        ShopAdminUser managerUser = shopAdminUserService.getByEmail(email);
        if (managerUser != null)
            return Response.ok(managerUser.getId()).status(200).build();
        else
            return Response.status(404).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ShopAdminUser managerUser) {
        ShopAdminUser managerUser1 = shopAdminUserService.update(managerUser);
        if (managerUser1 != null)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ShopAdminUser managerUser) {
        boolean res = shopAdminUserService.delete(managerUser);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }
}
