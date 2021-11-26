package com.quizonline.group8.repository;

import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.QuizQuestionList;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewCategoryRepo extends JpaRepository <QuizCategory, Long> {

//    @Query(value = "select ok.exam_id,ok.examName,ok.examTime,ok.numQuest,ok.timeCreate,ok.subjectID\n" +
    @Query(value = "select* \n"+
            "from QuizCategory \n" +
            "where subject_Id= :id" , nativeQuery = true)
    List<QuizCategory> findBySubject_id(@Param("id") Long id);

}
