package com.kpi.education.web.config;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

import javax.ws.rs.core.Response;

public class ResponseCorsFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {

        Response.ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
        resp.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");

        //headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org

        contResp.setResponse(resp.build());
        return contResp;
    }



}
