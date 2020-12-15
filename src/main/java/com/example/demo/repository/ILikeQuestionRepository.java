package com.example.demo.repository;

import com.example.demo.model.LikeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeQuestionRepository extends JpaRepository<LikeQuestion, Long> {
}
