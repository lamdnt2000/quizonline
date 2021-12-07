package com.quizonline.group8.repository;

import com.quizonline.group8.model.Member;
import com.quizonline.group8.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByMember(Member member);
    @Query(value = "SELECT * FROM Quiz Q WHERE Q.quiz_Id=:id AND Q.email=:member AND Q.status=:status",nativeQuery = true)
    Quiz findByIdAndMember(@Param("id") Long id, @Param("member") Member member,@Param("status")Integer status);
}
