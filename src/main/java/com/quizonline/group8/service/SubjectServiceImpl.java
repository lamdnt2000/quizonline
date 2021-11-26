package com.quizonline.group8.service;

import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements Subservice{
    @Autowired
    private SubjectRepository repo;

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = (List<Subject>) repo.findAll();
        return subjects;
    }

    @Override
    public Optional<Subject> findById(Long id) {
        Optional<Subject> subject = repo.findById(id);
        return subject;
    }

    @Override
    public Subject createSubject(Subject subjects) {
        return repo.save(subjects);
    }

}
