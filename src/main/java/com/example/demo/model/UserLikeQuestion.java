package com.example.demo.model;

import lombok.Data;

@Data
public class UserLikeQuestion {
    private User user;
    private Question question;
    private boolean isLiked;
}
