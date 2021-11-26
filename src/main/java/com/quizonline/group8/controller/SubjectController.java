package com.quizonline.group8.controller;

import com.quizonline.group8.dto.ReponseDTO;
import com.quizonline.group8.dto.ReponseSubjectDTO;
import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.mapper.impl.SubjectResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.SubjectResponseModel;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectController {
    @Autowired
    private SubjectResponseModelMapper subjectResponseModelMapper;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping(value="/")
    public ResponseEntity<List<Subject>> viewSubject(){

        return ResponseEntity.ok().body(subjectRepository.findAll());
    }

    @PostMapping(value="/createsubject")
    public ResponseEntity<ReponseDTO> save(@RequestBody ReponseSubjectDTO dto){
        Subject subject=new Subject();
        subject.setSubject_Id(dto.getSubject_Id());
        subject.setSubjectName(dto.getSubjectName());
        subject.setDateCreate(dto.getDateCreate());
        subject=subjectService.saveSubject(subject);
        ReponseDTO reponseDTO=new ReponseDTO();
        reponseDTO.setData(subject);
        reponseDTO.setSuccessCode("create subject success");
        return ResponseEntity.ok().body(reponseDTO);
    }
    @GetMapping(value ="/get/{subject_Id}")
    public ResponseEntity<Subject> getBySubject(@PathVariable(value="subject_Id") Long subject_Id ){
        System.out.println(subject_Id);
        return ResponseEntity.ok().body(subjectService.getSubById(subject_Id));
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<SubjectResponseModel>> findAll(){
        List<SubjectDTO> subjectDTOS = this.subjectService.findAllSubject();
        List<SubjectResponseModel> subjectResponseModels = this.subjectResponseModelMapper.toResponseModel(subjectDTOS);
        return ResponseEntity.ok(subjectResponseModels);
    }
}

