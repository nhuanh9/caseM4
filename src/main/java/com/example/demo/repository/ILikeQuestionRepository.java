package com.example.demo.repository;

import com.example.demo.model.LikeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeQuestionRepository extends JpaRepository<LikeAnswer, Long> {
    Iterable<LikeAnswer> findAllByUserId(Long id);
    LikeAnswer findByUserIdAndAnswerId(Long idUser, Long answerId);
}
