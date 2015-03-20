package com.foodservice.rest.resources;

import com.foodservice.businesslogic.data.State;
import com.foodservice.businesslogic.user.ManagerUser;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.exceptions.NoSuchUserException;
import com.foodservice.services.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/users/manager")
public class ManagerUserResource {

    private ManagerUserService managerUserService;

    @Autowired
    public void setManagerUserService(ManagerUserService managerUserService) {
        this.managerUserService = managerUserService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("shopAdminUserEmail") String shopAdminUserEmail,
                           ManagerUser managerUser) {
        try {
            ManagerUser managerUser1 = managerUserService.create(managerUser, shopAdminUserEmail);
            return Response.ok(managerUser1).status(200).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(403).build();
        } catch (NoSuchUserException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Returns list of ManagerUser related to ShopAdmin with particular id
     * @param shopAdminID identifier of ShopAdminUser
     * @return list of managers
     */
    @GET
    @Path("/byShopAdminID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByShopAdminUserID(@QueryParam("shopAdminID") int shopAdminID) {
        try {
            List<ManagerUser> managerUsers = managerUserService.getByShopAdminUserID(shopAdminID);
            if (managerUsers != null)
                throw new NoSuchUserException();
            return Response.ok(managerUsers).status(200).build();
        } catch (NoSuchUserException e) {
            return Response.status(404).build();        }
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        ManagerUser managerUser = managerUserService.get(id);
        if (managerUser != null)
            return Response.ok(managerUser).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("email") String email) {
        ManagerUser managerUser = managerUserService.getByEmail(email);
        if (managerUser != null)
            return Response.ok(managerUser.getId()).status(200).build();
        else
            return Response.status(404).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ManagerUser managerUser) {
        ManagerUser managerUser1 = managerUserService.update(managerUser);
        if (managerUser1 != null)
            return Response.status(200).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/state/{id}")
    public Response updateState(@PathParam("id") Integer id,
                                @QueryParam("state") State state) {
        ManagerUser managerUser1 = managerUserService.updateState(id, state);
        if (managerUser1 != null)
            return Response.status(200).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ManagerUser managerUser) {
        boolean res = managerUserService.delete(managerUser);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }
}
