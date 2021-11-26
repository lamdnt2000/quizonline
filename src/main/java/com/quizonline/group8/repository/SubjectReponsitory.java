package com.quizonline.group8.repository;

import com.quizonline.group8.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectReponsitory extends JpaRepository<Subject,Long> {
}
