package com.example.demo.controller;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.service.answer.AnswerService;
import com.example.demo.service.question.QuestionService;
import com.example.demo.service.role.RoleService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/answers")
public class AnswerController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Answer>> getAllAnswer() {
        Iterable<Answer> listAnswer = answerService.findAll();
        return new ResponseEntity<>(listAnswer, HttpStatus.OK);
    }


    @GetMapping("/question/{id}")
    public ResponseEntity<Iterable<Answer>> getAllAnswerByQuestionId(@PathVariable("id") Long id) {
        Iterable<Answer> listAnswer = answerService.getAnswerByQuestionId(id);
        return new ResponseEntity<>(listAnswer, HttpStatus.OK);
    }


}
