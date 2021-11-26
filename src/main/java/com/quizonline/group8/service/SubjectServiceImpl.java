package com.quizonline.group8.service;


import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectReponsitory subjectReponsitory;
    @Override
    public List<Subject> getAllSubject(){
        return subjectReponsitory.findAll();
    }
    @Override
    public Subject saveSubject(Subject subject){
        this.subjectReponsitory.save(subject);
        return subject;
    }
    @Override
    public Subject getSubById(Long subject_Id){
        Optional<Subject> optional=subjectReponsitory.findById(subject_Id);
        Subject subject=null;
        if(optional.isPresent()){
            subject = optional.get();
        }else {
            throw  new RuntimeException("Subject not found for id"+subject_Id);
        } return subject;
    }

}
