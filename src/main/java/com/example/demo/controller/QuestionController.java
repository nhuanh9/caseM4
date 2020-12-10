package com.example.demo.controller;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.question.QuestionService;
import com.example.demo.service.role.RoleService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@CrossOrigin("*")
@RequestMapping("/users/questions")
public class QuestionController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Question>> getAllQuestion() {
        Iterable<Question> listQuestion = questionService.findAll();
        return new ResponseEntity<>(listQuestion, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Question>> getAllAnswerByQuestionId(@PathVariable("id") Long id) {
        Iterable<Question> listQuestion = questionService.getQuestionByUserId(id);
        return new ResponseEntity<>(listQuestion, HttpStatus.OK);
    }


}
