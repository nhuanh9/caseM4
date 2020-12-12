package com.example.demo.service.question;

import com.example.demo.model.Question;
import com.example.demo.model.Role;
import com.example.demo.service.IGeneralService;

public interface IQuestionService extends IGeneralService<Question> {
    Iterable<Question> getQuestionByUserId(Long id);
    Iterable<Question> getQuestionByCategoryId(Long id);
}
