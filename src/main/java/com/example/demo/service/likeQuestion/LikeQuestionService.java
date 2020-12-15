package com.example.demo.service.likeQuestion;

import com.example.demo.model.LikeQuestion;
import com.example.demo.model.Question;
import com.example.demo.repository.ILikeQuestionRepository;
import com.example.demo.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeQuestionService implements ILikeQuestionService{
    @Autowired
    ILikeQuestionRepository likeQuestionRepository;

    @Override
    public Iterable<LikeQuestion> findAll() {
        return likeQuestionRepository.findAll();
    }

    @Override
    public LikeQuestion save(LikeQuestion likeQuestion) {
        return likeQuestionRepository.save(likeQuestion);
    }

    @Override
    public Optional<LikeQuestion> findById(Long id) {
        return likeQuestionRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        likeQuestionRepository.deleteById(id);
    }
}
