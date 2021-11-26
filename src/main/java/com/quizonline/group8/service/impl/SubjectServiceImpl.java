package com.quizonline.group8.service.impl;

import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.mapper.impl.SubjectDTOMapper;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
