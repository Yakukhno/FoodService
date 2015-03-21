package com.foodservice.rest.resources;

import com.foodservice.businesslogic.data.SystemStatus;
import com.foodservice.services.UserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Component
@Path("/users")
public class UserMetaResource {

    private UserCommonService userCommonService;

    @Autowired
    public void setUserCommonService(UserCommonService userCommonService) {
        this.userCommonService = userCommonService;
    }

    @GET
    @Path("/statuses")
    @Produces("application/json")
    public Response getSystemStatus(Integer[] identifiers) {
        SystemStatus[] statuses = userCommonService.getSystemStatus(identifiers);
        return Response.ok(statuses).status(Response.Status.OK).build();
    }

    @PUT
    @Path("/statuses")
    public Response changeSystemStatus(@QueryParam("id")Integer id,
                                       @QueryParam("status") SystemStatus status) {
        boolean res = userCommonService.changeSystemStatus(id, status);
        if (res == true) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
