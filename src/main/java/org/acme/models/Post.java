package org.acme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Post {
    @JsonIgnore
    Integer id;
    Integer userId;
    String title;
    boolean completed;
}
