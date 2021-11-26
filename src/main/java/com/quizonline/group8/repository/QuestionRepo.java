package com.quizonline.group8.repository;

import com.quizonline.group8.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    @Query(value = "select * from Question q where q.questionTitle like %:questionTitle% and q.subject_id = :sub_Id",nativeQuery = true)
    List<Question> findAllByQuestionTitle(@Param("questionTitle")String questionTitle,
                                          @Param("sub_Id") Long sub_id);

}
