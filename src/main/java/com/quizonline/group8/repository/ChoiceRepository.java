package com.quizonline.group8.repository;

import com.quizonline.group8.model.Choise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choise, Long> {
}
