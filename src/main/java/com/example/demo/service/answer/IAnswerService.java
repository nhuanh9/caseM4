package com.example.demo.service.answer;

import com.example.demo.model.Answer;
import com.example.demo.model.Role;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IAnswerService extends IGeneralService<Answer> {
    Iterable<Answer> getAnswerByQuestionId(Long id);
    Iterable<Answer> getAnswerByUserId(Long id);
    List<Answer> findAll();
}
