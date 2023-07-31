package org.acme.client;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exceptions.PostDoesNotExistException;
import org.acme.models.Post;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "placeholder-client")
public interface PlaceholderClient {

    @Path("/posts")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    List<Post> getPosts();

    @Path("/posts/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    Post getPostById(Integer id);


    @Path("/posts")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createPost(Post post);

    @ClientExceptionMapper
    static PostDoesNotExistException toException(Response response) {
        if (response.getStatus() == 404) {
            return new PostDoesNotExistException("this post does not exist");
        }
        return null;
    }
}
