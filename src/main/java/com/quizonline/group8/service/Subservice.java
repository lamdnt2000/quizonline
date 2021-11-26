package com.quizonline.group8.service;

import com.quizonline.group8.model.Choise;
import com.quizonline.group8.model.Subject;

import java.util.List;
import java.util.Optional;

public interface Subservice {
    public List<Subject> findAll();
    public Optional<Subject> findById(Long id);
    Subject createSubject(Subject subjects);
}
