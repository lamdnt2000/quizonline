package com.quizonline.group8.service;
import com.quizonline.group8.dto.QuerySearchDTO;
import com.quizonline.group8.model.Subject;
import org.springframework.stereotype.Service;
import com.quizonline.group8.mapper.dto.SubjectDTO;
import java.util.List;

@Service
public interface SubjectService {
    Integer countSubject(QuerySearchDTO querySearchDTO);
    List<Subject> searchSubjectByTitle(QuerySearchDTO querySearchDTO);
    Subject saveSubject(Subject subject);
    Subject getSubById(Long subject_Id);
    List<SubjectDTO> findAllSubject();
    List<Subject> findAll();
}

