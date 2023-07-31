package org.acme.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.client.PlaceholderClient;
import org.acme.models.Post;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PostServiceImplTest {

    @InjectMock
    @RestClient
    PlaceholderClient placeholderClient;

    @Inject
    PostService postService;

    @Test
    void getPost() {
        Post post = new Post();
        post.setId(1);
        post.setUserId(2);
        post.setTitle("title");
        post.setCompleted(true);
        Mockito.when(placeholderClient.getPostById(1)).thenReturn(post);
        assertEquals(postService.getPostById(1), post);
    }

    @Test
    void getAllPosts() {
        List<Post> posts = createPosts(10);
        Mockito.when(placeholderClient.getPosts()).thenReturn(posts);
        assertEquals(postService.getPosts().size(), 10);
    }


    List<Post> createPosts(int numberOfPosts) {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= numberOfPosts; i++) {
            Post post = new Post();
            post.setId(i);
            post.setUserId(i);
            post.setTitle("title " + i);
            post.setCompleted(false);
            posts.add(post);
        }
    return posts;
    }
}