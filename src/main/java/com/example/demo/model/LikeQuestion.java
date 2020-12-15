package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikeQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long like_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
