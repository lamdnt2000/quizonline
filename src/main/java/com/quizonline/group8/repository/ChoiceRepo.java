package com.quizonline.group8.repository;

import com.quizonline.group8.model.Choise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepo extends JpaRepository<Choise, Long> {
}
