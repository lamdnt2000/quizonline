package com.quizonline.group8.controller;

import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.mapper.impl.SubjectResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.SubjectResponseModel;
import com.quizonline.group8.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectResponseModelMapper subjectResponseModelMapper;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/findAll")
    private ResponseEntity<List<SubjectResponseModel>> findAll(){
        List<SubjectDTO> subjectDTOS = this.subjectService.findAllSubject();
        List<SubjectResponseModel> subjectResponseModels = this.subjectResponseModelMapper.toResponseModel(subjectDTOS);
        return ResponseEntity.ok(subjectResponseModels);
    }
}
