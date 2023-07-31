package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.client.PlaceholderClient;
import org.acme.entity.MyPost;
import org.acme.models.Post;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import java.util.List;

@ApplicationScoped
public class PostServiceImpl implements PostService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(PostServiceImpl.class);

    private final PlaceholderClient placeholderClient;

    public PostServiceImpl(@RestClient PlaceholderClient placeholderClient) {
        this.placeholderClient = placeholderClient;
    }

    @Override
    public List<Post> getPosts() {
        return placeholderClient.getPosts();
    }

    @Override
    public Post getPostById(Integer id) {
        return placeholderClient.getPostById(id);
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        try (Response res = placeholderClient.createPost(post)) {

            logger.info("Saving new Post in DB..");

            MyPost myPost = new MyPost(post.getUserId(), post.getTitle());
            myPost.persist();

            return res.readEntity(Post.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
