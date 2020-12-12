package com.example.demo.service.question;

import com.example.demo.model.Question;
import com.example.demo.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class QuestionService implements IQuestionService{
    @Autowired
    IQuestionRepository questionRepository;
    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Iterable<Question> getQuestionByUserId(Long id) {
        return questionRepository.getQuestionByUserId(id);
    }

    @Override
    public Iterable<Question> getQuestionByCategoryId(Long id) {
        return questionRepository.getQuestionByCategoryId(id);
    }
}
