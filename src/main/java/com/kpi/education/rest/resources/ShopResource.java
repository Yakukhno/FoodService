package com.kpi.education.rest.resources;

import com.kpi.education.businesslogic.Shop;
import com.kpi.education.exceptions.DuplicatedKeyException;
import com.kpi.education.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/shops")
public class ShopResource {
    
    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Shop shop, @QueryParam("managerUserID") int managerUserID) {
        System.out.println(shop);
        try {
            Shop shop1 = shopService.create(shop, managerUserID);
            return Response.ok(shop1.getId()).status(200).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(403).build();
        }
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("id") int id) {
        Shop shop = shopService.get(id);
        if (shop != null)
            return Response.ok(shop).status(200).build();
        else
            return Response.status(404).build();
    }

    @GET
    @Path("/byManagerID/{managerID}")
    public Response getByManagerID(@PathParam("managerID") Integer managerID) {
        List<Shop> result = shopService.getByManagerID(managerID);
        Shop[] array = new Shop[result.size()];
        for (int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        if (result.size() != 0) {
            return Response.ok(array).status(200).build();
        } else {
            return Response.status(404).build();
        }
    }
    
    @GET
    @Path("/byCriterion")
    public Response getByCriterion(@DefaultValue("") @QueryParam("nameLike") String name,
                                   @DefaultValue("0") @QueryParam("minRating") int minRating,
                                   @DefaultValue("5") @QueryParam("maxRating") int maxRating,
                                   @DefaultValue("") @QueryParam("countryLike") String country,
                                   @DefaultValue("") @QueryParam("cityLike") String city,
                                   @DefaultValue("") @QueryParam("streetLike") String street,
                                   @DefaultValue("") @QueryParam("buildingLike") String building,
                                   @DefaultValue("0") @QueryParam("firstResult") int firstResut,
                                   @DefaultValue("0") @QueryParam("maxResults") int maxResults) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("nameLike", name);
        parameters.put("minRating", minRating);
        parameters.put("maxRating", maxRating);
        parameters.put("countryLike", country);
        parameters.put("cityLike", city);
        parameters.put("streetLike", street);
        parameters.put("buildingLike", building);
        List<Shop> result = shopService.getByCriterion(parameters);
        if (result.size() != 0) {
            return Response.ok(result).status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Shop shop) {
        Shop shop1 = shopService.update(shop);
        if (shop1 != null)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Shop shop) {
        boolean res = shopService.delete(shop);
        if (res)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }
}
