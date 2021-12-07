package com.quizonline.group8.repository;

import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface QuestionRepository extends PagingAndSortingRepository<Question,Long> {
    List<Question> findAllBySubject(Subject subject, Pageable pageable);
    @Query(value="SELECT TOP (:numberOfQuest) * FROM Question q WHERE q.subject_Id=:subjectId ORDER BY NEWID()",nativeQuery = true)
    List<Question> findByTopOrderByRand(@Param("numberOfQuest") Integer numberOfQuest,@Param("subjectId")  Long subjectId);
    List<Question> findByQuestionTitleContainingAndSubjectAndStatus(String questionTitle, Subject subject_id, Integer status, Pageable pageable);
    Integer countByQuestionTitleContainingAndSubjectAndStatus(String questionTitle, Subject subject_id, Integer status);
    @Query(value = "SELECT COUNT(*) FROM Question q WHERE q.quest_ID=:questId AND q.correctAnswer=:choice",nativeQuery = true)
    Integer countByQuest_IDAAndCorrectAnswer(@Param("questId") Long questId, @Param("choice") Integer choice);
}
