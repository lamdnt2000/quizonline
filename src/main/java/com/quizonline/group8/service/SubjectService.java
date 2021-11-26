package com.quizonline.group8.service;
import com.quizonline.group8.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    List<Subject> getAllSubject();
    Subject saveSubject(Subject subject);
    Subject getSubById(Long subject_Id);
}
