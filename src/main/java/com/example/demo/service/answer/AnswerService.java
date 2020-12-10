package com.example.demo.service.answer;

import com.example.demo.model.Answer;
import com.example.demo.repository.IAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AnswerService implements IAnswerService {
    @Autowired
    IAnswerRepository iAnswerRepository;
    @Override
    public Iterable<Answer> findAll() {
        return iAnswerRepository.findAll();
    }

    @Override
    public Answer save(Answer answer) {
        return iAnswerRepository.save(answer);
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return iAnswerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iAnswerRepository.deleteById(id);
    }

    @Override
    public Iterable<Answer> getAnswerByQuestionId(Long id) {
        return iAnswerRepository.getAnswerByQuestionId(id);
    }

    @Override
    public Iterable<Answer> getAnswerByUserId(Long id) {
        return iAnswerRepository.getAnswerByUserId(id);
    }
}
