package com.quizonline.group8.repository;

import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizCategoryRepository extends JpaRepository<QuizCategory,Long> {
    public List<QuizCategory> findBySubject(Subject subject);
    List<QuizCategory> findByExamNameContainingAndSubjectOrderByTimeCreateDesc(String examName, Subject subject_id, Pageable pageable);
    Integer countByExamNameContainingAndSubject(String examName, Subject subject_id);
}
