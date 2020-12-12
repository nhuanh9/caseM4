package com.example.demo.repository;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository  extends JpaRepository<Question, Long> {
    Iterable<Question> getQuestionByUserId(Long id);
    Iterable<Question> getQuestionByCategoryId(Long id);
}
