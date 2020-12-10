package com.example.demo.service.answer;

import com.example.demo.model.Answer;
import com.example.demo.model.Role;
import com.example.demo.service.IGeneralService;

public interface IAnswerService extends IGeneralService<Answer> {
    Iterable<Answer> getAnswerByQuestionId(Long id);
    Iterable<Answer> getAnswerByUserId(Long id);
}
