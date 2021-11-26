package com.quizonline.group8.repository;

import com.quizonline.group8.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findById(Long subject_Id);
}
