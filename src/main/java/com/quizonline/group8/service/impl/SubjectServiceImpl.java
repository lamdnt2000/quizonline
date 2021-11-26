package com.quizonline.group8.service.impl;

import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.mapper.impl.SubjectDTOMapper;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectDTOMapper subjectDTOMapper;
    @Override
    public List<SubjectDTO> findAllSubject() {
        List<Subject> subjects = this.subjectRepository.findAll();
        List<SubjectDTO> subjectDTOS = this.subjectDTOMapper.toDTO(subjects);
        return subjectDTOS;
    }
    @Override
    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }
    @Override
    public Subject saveSubject(Subject subject){
        this.subjectRepository.save(subject);
        return subject;
    }
    @Override
    public Subject getSubById(Long subject_Id){
        Optional<Subject> optional=subjectRepository.findById(subject_Id);
        Subject subject=null;
        if(optional.isPresent()){
            subject = optional.get();
        }else {
            throw  new RuntimeException("Subject not found for id"+subject_Id);
        } return subject;
    }
}
