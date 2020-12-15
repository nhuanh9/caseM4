package com.example.demo.model;

import lombok.Data;

@Data
public class UserLikeAnswer {
    private User user;
    private Answer answer;
    private boolean isLiked;
}
