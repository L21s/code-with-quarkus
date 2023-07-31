package org.acme.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.acme.controller.responses.ErrorResponse;
import org.acme.exceptions.PostDoesNotExistException;
import org.acme.models.Post;
import org.acme.service.PostService;

import java.util.List;

@Path("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GET
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GET
    @Path("/{id}")
    public Response getPosts(@PathParam("id") Integer id) {
        try {
            Post post = postService.getPostById(id);
            return Response.ok(post).build();
        } catch (PostDoesNotExistException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

    @POST
    public Response createPost(Post post) {
        try {
            Post postResponse = postService.createPost(post);
            return Response.ok(postResponse).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

}
