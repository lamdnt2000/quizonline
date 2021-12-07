package com.quizonline.group8.repository;

import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizCategoryRepository extends JpaRepository<QuizCategory,Long> {
    public List<QuizCategory> findBySubject(Subject subject);
    List<QuizCategory> findByExamNameContainingAndSubject(String examName, @Param("subject_id") Subject subject_id, Pageable pageable);
    Integer countByExamNameContainingAndSubject(String examName, @Param("subject_id")Subject subject_id);
}
