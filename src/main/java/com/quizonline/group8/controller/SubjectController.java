package com.quizonline.group8.controller;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.ReponseDTO;
import com.quizonline.group8.dto.QuerySearchDTO;
import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.mapper.impl.SubjectDTOMapper;
import com.quizonline.group8.mapper.impl.SubjectResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.SubjectResponseModel;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.SubjectService;
import com.quizonline.group8.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectController {
    @Autowired
    private SubjectResponseModelMapper subjectResponseModelMapper;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectDTOMapper subjectDTOMapper;
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping(value="/create",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ReponseDTO> saveSubject(@Valid SubjectDTO dto){
        Subject subject= subjectDTOMapper.toEntity(dto);
        subject.setDateCreate(TimeUtils.getCurrentTime());
        subject=subjectService.saveSubject(subject);
        ReponseDTO reponseDTO=new ReponseDTO();
        reponseDTO.setData(subject);
        reponseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok().body(reponseDTO);
    }

    @PutMapping(value="/update",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ReponseDTO> updateSubject(@Valid SubjectDTO dto){
        Subject subject= subjectDTOMapper.toEntity(dto);
        ReponseDTO reponseDTO=new ReponseDTO();
        Subject oldSubject = subjectService.getSubById(subject.getSubject_Id());
        if (oldSubject!=null) {
            subject.setDateCreate(oldSubject.getDateCreate());
            subject = subjectService.saveSubject(subject);
            reponseDTO.setData(subject);
            reponseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        }
        else{
            reponseDTO.setData("Subject not found");
            reponseDTO.setErrorCode(Constants.FAIL_CODE);
        }
        return ResponseEntity.ok().body(reponseDTO);
    }
    @GetMapping(value ="/get/{subject_Id}")
    public ResponseEntity<Subject> getBySubject(@PathVariable(value="subject_Id") Long subject_Id ){
        return ResponseEntity.ok().body(subjectService.getSubById(subject_Id));
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<SubjectResponseModel>> findAll(){
        List<SubjectDTO> subjectDTOS = this.subjectService.findAllSubject();
        List<SubjectResponseModel> subjectResponseModels = this.subjectResponseModelMapper.toResponseModel(subjectDTOS);
        return ResponseEntity.ok(subjectResponseModels);
    }

    @GetMapping(value="/search",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<Subject>> getByTitle(@Valid QuerySearchDTO querySearchDTO){
        return ResponseEntity.ok().body(subjectService.searchSubjectByTitle(querySearchDTO));
    }

    @GetMapping(value="/count",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Integer> getTotalQuestion(@Valid QuerySearchDTO querySearchDTO){
        return ResponseEntity.ok().body(subjectService.countSubject(querySearchDTO));
    }
}

