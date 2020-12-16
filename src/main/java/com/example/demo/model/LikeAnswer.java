package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikeAnswer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long like_id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
