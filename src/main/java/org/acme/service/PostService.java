package org.acme.service;

import jakarta.ws.rs.core.Response;
import org.acme.models.Post;

import java.util.List;

public interface PostService {

    List<Post> getPosts();

    Post getPostById(Integer id);

    Post createPost(Post post);

}
