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
import java.util.Collections;
import java.util.List;

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

    private boolean checkLike(User user, Answer answer, Iterable<LikeAnswer> likeQuestions) {
        for (LikeAnswer i : likeQuestions) {
            if (i.getAnswer() == answer && i.getUser() == user && i.isLiked()) {
                return false;
            }
        }
        return true;
    }

    private List<UserLikeAnswer> userLikeAnswers(User user, Iterable<Answer> answers, Iterable<LikeAnswer> likeAnswers) {
        List<UserLikeAnswer> userLikeAnswers = new ArrayList<>();
        for (Answer i : answers) {
            UserLikeAnswer userLikeAnswer = new UserLikeAnswer();
            userLikeAnswer.setAnswer(i);
            userLikeAnswer.setUser(user);
            for (LikeAnswer j : likeAnswers) {
                if (j.getUser() == user && j.getAnswer() == i && j.isLiked()) {
                    userLikeAnswer.setLiked(true);
                }
            }

            userLikeAnswers.add(userLikeAnswer);
        }
        return userLikeAnswers;
    }

    @PostMapping("/like/{id}")
    public ModelAndView likePost(@ModelAttribute("likePost") LikeAnswer like, @PathVariable Long id) {
        Answer answer = answerService.findById(id).get();
        User user = userService.findById(getCurrentUser().getId()).get();
        LikeAnswer lastLike = likeQuestionService.findByUserIdAndAnswerId(user.getId(), answer.getId());
        if (checkLike(user, answer, likeQuestionService.findAll())) {
            if (lastLike == null) {
                like.setAnswer(answer);
                like.setUser(user);
                like.setLiked(true);
                likeQuestionService.save(like);
            } else  {
                lastLike.setLiked(true);
                likeQuestionService.save(lastLike);
            }
            Long oldLikes = answer.getLikes();
            oldLikes = oldLikes == null ? Long.valueOf(0) : oldLikes;
            answer.setLikes(oldLikes + Long.valueOf(1));
            answerService.save(answer);

        }
        List<Answer> answers = answerService.getAnswerByQuestionId(answer.getQuestion().getId());
        ModelAndView modelAndView = new ModelAndView("question/detail");
        modelAndView.addObject("question", answer.getQuestion());
        Collections.sort(answers);
        modelAndView.addObject("answers", userLikeAnswers(user, answers, likeQuestionService.findAll()));
        modelAndView.addObject("answersCount", size(answers));
        modelAndView.addObject("newAnswer", new Answer());
        return modelAndView;
    }

    @PostMapping("/dislike/{id}")
    public ModelAndView dislikePost(@ModelAttribute("likePost") LikeAnswer like, @PathVariable Long id) {
        Answer answer = answerService.findById(id).get();
        User user = userService.findById(getCurrentUser().getId()).get();
        LikeAnswer likeAnswer = likeQuestionService.findByUserIdAndAnswerId(user.getId(), answer.getId());
        if (!checkLike(user, answer, likeQuestionService.findAll())) {
            likeAnswer.setLiked(false);
            Long oldLikes = answer.getLikes();
            oldLikes = oldLikes == null ? Long.valueOf(0) : oldLikes;
            answer.setLikes(oldLikes - Long.valueOf(1));
            answerService.save(answer);
            likeQuestionService.save(likeAnswer);
        }
        List<Answer> answers = answerService.getAnswerByQuestionId(answer.getQuestion().getId());
        ModelAndView modelAndView = new ModelAndView("question/detail");
        modelAndView.addObject("question", answer.getQuestion());
        Collections.sort(answers);
        modelAndView.addObject("answers", userLikeAnswers(user, answers, likeQuestionService.findAll()));
        modelAndView.addObject("answersCount", size(answers));
        modelAndView.addObject("newAnswer", new Answer());
        return modelAndView;
    }

}
