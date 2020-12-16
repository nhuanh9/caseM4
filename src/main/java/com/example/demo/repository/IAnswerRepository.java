package com.example.demo.repository;

import com.example.demo.model.Answer;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerRepository  extends JpaRepository<Answer, Long> {
    List<Answer> getAnswerByQuestionId(Long id);
    List<Answer> getAnswerByUserId(Long id);
}
