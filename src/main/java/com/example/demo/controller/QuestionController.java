package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.answer.AnswerService;
import com.example.demo.service.question.QuestionService;
import com.example.demo.service.role.RoleService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.attribute.UserPrincipal;
import java.util.*;

@Controller
@RequestMapping("/user/questions")
public class QuestionController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    //    @GetMapping("/")
//    public ResponseEntity<Iterable<Question>> getAllQuestion() {
//        Iterable<Question> listQuestion = questionService.findAll();
//        return new ResponseEntity<>(listQuestion, HttpStatus.OK);
//    }

    private int size(Iterable<Answer> data) {

        if (data instanceof Collection) {
            return ((Collection<?>) data).size();
        }
        int counter = 0;
        for (Object i : data) {
            counter++;
        }
        return counter;
    }

    private User getCurrentUser() {
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPrinciple userPrinciple= (UserPrinciple) userPrincipal;
        User user = userService.findByUsername(userPrinciple.getUsername());
        return user;
    }

    @GetMapping("/")
    public ModelAndView getAllQuestion() {
//        getCurrentUser();
        Iterable<Question> listQuestion = questionService.findAll();
        ModelAndView modelAndView = new ModelAndView("question/list");
        modelAndView.addObject("questions", listQuestion);
        return modelAndView;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Question>> getAllAnswerByQuestionId(@PathVariable("id") Long id) {
        Iterable<Question> listQuestion = questionService.getQuestionByUserId(id);
        return new ResponseEntity<>(listQuestion, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ModelAndView getQuestionById(@PathVariable("id") Long id) {
        Optional<Question> question = questionService.findById(id);
        Iterable<Answer> answers = answerService.getAnswerByQuestionId(id);
        ModelAndView modelAndView = new ModelAndView("question/detail");
        modelAndView.addObject("question", question.get());
        modelAndView.addObject("answers", answers);
        modelAndView.addObject("answersCount", size(answers));
        modelAndView.addObject("newAnswer", new Answer());
        return modelAndView;
    }

    @PostMapping("/{id}/add-answer")
    public ModelAndView addAnswer(@ModelAttribute Answer answer, @PathVariable Long id) {
        Optional<Question> question = questionService.findById(id);
        answer.setId(null);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        answer.setDate(date);
        answer.setUser(getCurrentUser());
        answer.setQuestion(question.get());
        answerService.save(answer);
        Iterable<Answer> answers = answerService.getAnswerByQuestionId(id);
        ModelAndView modelAndView = new ModelAndView("question/detail");
        modelAndView.addObject("question", question.get());
        modelAndView.addObject("answers", answers);
        modelAndView.addObject("answersCount", size(answers));
        modelAndView.addObject("newAnswer", new Answer());
        return modelAndView;
    }
}
