package com.kpi.education.rest;


import com.kpi.education.businesslogic.data.Gender;
import com.kpi.education.businesslogic.user.SimpleUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
public class SimpleUserResourceTest {

    @Test
    public void test() {

        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setFirstName("SimpleUser firstname");
        simpleUser.setGender(Gender.MALE);
        simpleUser.setLastName("SimpleUser lastname");

        ///////////////////////////////////TEST POST/////////////////////////////////////
        //convert object to json
        String json = null;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(simpleUser);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Client client = Client.create();
        WebResource webResource = client
                .resource("http://localhost:8081/FoodService/resources/user/simple/create");

        ClientResponse response = webResource.type("application/json")
                .accept("application/json")
                .post(ClientResponse.class, json);

        assertTrue(response.getStatus() == 200);

        String output = response.getEntity(String.class);


        ObjectReader or = new ObjectMapper().reader().withType(SimpleUser.class);
        SimpleUser simpleUser1 = null;
        try {
            simpleUser1 = or.readValue(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(simpleUser1.getFirstName().equals(simpleUser.getFirstName()));

        ///////////////////////////////////TEST GET/////////////////////////////////////
        webResource = client
                .resource("http://localhost:8081/FoodService/resources/" +
                        "user/simple/retrieve/byid/" + simpleUser1.getId());
        response = webResource.type("application/json")
                .accept("application/json")
                .get(ClientResponse.class);
        assertTrue(response.getStatus() == 200);

        output = response.getEntity(String.class);
        SimpleUser simpleUser2 = null;
        try {
            simpleUser2 = or.readValue(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(simpleUser1, simpleUser2);


        ///////////////////////////////////TEST UPDATE/////////////////////////////////////
        simpleUser2.setFirstName("UPDATED USER");
        try {
            json = ow.writeValueAsString(simpleUser2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        webResource = client
                .resource("http://localhost:8081/FoodService/resources/" +
                        "user/simple/update");
        response = webResource.type("application/json")
                .accept("application/json")
                .put(ClientResponse.class, json);
        assertTrue(response.getStatus() == 200);

        output = response.getEntity(String.class);
        SimpleUser simpleUser3 = null;
        try {
            simpleUser3 = or.readValue(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(simpleUser2, simpleUser3);

        ///////////////////////////////////TEST DELETE/////////////////////////////////////
        webResource = client
                .resource("http://localhost:8081/FoodService/resources/" +
                        "user/simple/delete");
        response = webResource.type("application/json")
                .delete(ClientResponse.class, json);
        assertTrue(response.getStatus() == 200);
    }
}
