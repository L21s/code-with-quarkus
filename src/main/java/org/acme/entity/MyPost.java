package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MyPost extends PanacheEntity {
    public Integer userId;
    public String title;
    public LocalDateTime createdAt;

    public MyPost() {
        createdAt = LocalDateTime.now();
    }
    public MyPost(Integer userId, String title) {
        this.userId = userId;
        this.title = title;
        createdAt = LocalDateTime.now();
    }
}
