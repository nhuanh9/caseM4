package com.example.demo.service.likeQuestion;

import com.example.demo.model.Answer;
import com.example.demo.model.LikeAnswer;
import com.example.demo.repository.ILikeQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeQuestionService implements ILikeQuestionService{
    @Autowired
    ILikeQuestionRepository likeQuestionRepository;

    @Override
    public Iterable<LikeAnswer> findAll() {
        return likeQuestionRepository.findAll();
    }

    @Override
    public LikeAnswer save(LikeAnswer likeAnswer) {
        return likeQuestionRepository.save(likeAnswer);
    }

    @Override
    public Optional<LikeAnswer> findById(Long id) {
        return likeQuestionRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        likeQuestionRepository.deleteById(id);
    }

    @Override
    public Iterable<LikeAnswer> findAllByUserId(Long id) {
        return likeQuestionRepository.findAllByUserId(id);
    }

    @Override
    public LikeAnswer findByUserIdAndAnswerId(Long idUser, Long answerId) {
        return likeQuestionRepository.findByUserIdAndAnswerId(idUser, answerId);
    }
}
