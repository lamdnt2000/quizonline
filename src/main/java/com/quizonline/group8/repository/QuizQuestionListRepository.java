package com.quizonline.group8.repository;

import com.quizonline.group8.model.QuizQuestionList;
import com.quizonline.group8.model.QuizQuestionListID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizQuestionListRepository extends JpaRepository<QuizQuestionList, QuizQuestionListID> {
    @Query(value = "SELECT * FROM QuizQuestionList q WHERE q.quiz_id = :quizId",nativeQuery = true)
    List<QuizQuestionList> findByExamId(@Param("quizId")Long quizId, Pageable pageable);


}
