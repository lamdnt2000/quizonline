package com.quizonline.group8.repository;

import com.quizonline.group8.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Optional<Subject> findById(Long subject_Id);
}
