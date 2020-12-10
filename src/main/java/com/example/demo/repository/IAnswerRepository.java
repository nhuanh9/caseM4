package com.example.demo.repository;

import com.example.demo.model.Answer;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository  extends JpaRepository<Answer, Long> {
    Iterable<Answer> getAnswerByQuestionId(Long id);
    Iterable<Answer> getAnswerByUserId(Long id);
}
