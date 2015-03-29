package com.foodservice.rest.resources;

import com.foodservice.businesslogic.data.SystemStatus;
import com.foodservice.businesslogic.data.UserType;
import com.foodservice.businesslogic.user.User;
import com.foodservice.services.UserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/users")
public class UserCommonResource {

    private UserCommonService userCommonService;

    @Autowired
    public void setUserCommonService(UserCommonService userCommonService) {
        this.userCommonService = userCommonService;
    }

    @GET
    @Path("/statuses")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getSystemStatus(Integer[] identifiers) {
        SystemStatus[] statuses = userCommonService.getSystemStatus(identifiers);
        return Response.ok(statuses).status(Response.Status.OK).build();
    }

    @PUT
    @Path("/statuses")
    public Response changeSystemStatus(@QueryParam("id") Integer id,
                                       @QueryParam("status") SystemStatus status) {
        boolean res = userCommonService.changeSystemStatus(id, status);
        if (res == true) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * Returns list of users who ever have a dialog with user related to this id
     * @param id userId
     * @return
     */
    @GET
    @Path("/messageRelated")
    @Produces("application/json")
    public Response getMessageRelatedUsers(@QueryParam("id") Integer id,
                                           @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                           @DefaultValue("1000000") @QueryParam("maxResults") int maxResults,
                                           @QueryParam("userType") UserType userType) {
        List<User> users = userCommonService.getMessageRelatedUsers(id, firstResult, maxResults, userType);
        return Response.ok(users).status(Response.Status.OK).build();
    }
}
