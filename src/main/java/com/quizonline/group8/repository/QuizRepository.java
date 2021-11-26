package com.quizonline.group8.repository;

import com.quizonline.group8.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {

}
