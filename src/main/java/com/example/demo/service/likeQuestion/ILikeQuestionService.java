package com.example.demo.service.likeQuestion;

import com.example.demo.model.Answer;
import com.example.demo.model.LikeAnswer;
import com.example.demo.service.IGeneralService;

public interface ILikeQuestionService extends IGeneralService<LikeAnswer> {
    Iterable<LikeAnswer> findAllByUserId(Long id);

    LikeAnswer findByUserIdAndAnswerId(Long idUser, Long answerId);

}
