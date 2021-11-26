package com.quizonline.group8.repository;

import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizCategoryRepository extends JpaRepository<QuizCategory,Long> {
    public List<QuizCategory> findBySubject(Subject subject);
}
