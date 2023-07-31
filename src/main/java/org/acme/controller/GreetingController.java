package org.acme.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.controller.responses.CustomResponse;
import org.acme.entity.MyPost;

import java.util.List;

@Path("/hello")
public class GreetingController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/custom")
    @Produces(MediaType.APPLICATION_JSON)
    public Response customHello() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus("success");
        customResponse.setMessage("Hello from RESTEasy Reactive");

        return Response.ok(customResponse).build();

    }

    @GET
    @Path("/myposts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MyPost> getMyPosts() {
        return MyPost.listAll();

    }

}
