package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.answer.AnswerService;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.likeQuestion.LikeQuestionService;
import com.example.demo.service.question.QuestionService;
import com.example.demo.service.role.RoleService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class LikeController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LikeQuestionService likeQuestionService;

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
        UserPrinciple userPrinciple = (UserPrinciple) userPrincipal;
        User user = userService.findByUsername(userPrinciple.getUsername());
        return user;
    }

    private boolean checkLike(User user, Answer answer, Iterable<LikeQuestion> likeQuestions) {
        for (LikeQuestion i: likeQuestions){
            if (i.getAnswer()==answer && i.getUser() == user && i.isLiked()) {
                return false;
            }
        }
        return true;
    }

    @PostMapping("/like/{id}")
    public ModelAndView likePost(@ModelAttribute("likePost") LikeQuestion like, @PathVariable Long id) {
        Answer answer = answerService.findById(id).get();
        User user = userService.findById(getCurrentUser().getId()).get();
        if (checkLike(user, answer, likeQuestionService.findAll())) {
            like.setAnswer(answer);
            like.setUser(user);
            like.setLiked(true);
            likeQuestionService.save(like);
        }
        Iterable<Answer> answers = answerService.getAnswerByQuestionId(answer.getQuestion().getId());
        ModelAndView modelAndView = new ModelAndView("question/detail");
        modelAndView.addObject("question", answer.getQuestion());
        modelAndView.addObject("answers", answers);
        modelAndView.addObject("answersCount", size(answers));
        modelAndView.addObject("newAnswer", new Answer());
        return modelAndView;
    }

}
