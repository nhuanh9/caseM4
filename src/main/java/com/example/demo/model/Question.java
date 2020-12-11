package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Date date;

    @OneToMany(targetEntity = Answer.class)
    private List<Answer> listAnswer;

}
