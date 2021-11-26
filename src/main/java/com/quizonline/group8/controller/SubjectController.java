package com.quizonline.group8.controller;

import com.quizonline.group8.dto.ReponseDTO;
import com.quizonline.group8.dto.ReponseSubjectDTO;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectReponsitory;
import com.quizonline.group8.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectReponsitory subjectReponsitory;
    //        @RequestMapping("/")
//    public ModelAndView index(){
//        return new ModelAndView("viewsubject","listSubject",subjectReponsitory.findAll());
//    }
    @GetMapping(value="/")
    public ResponseEntity<List<Subject>> viewSubject(){

        return ResponseEntity.ok().body(subjectReponsitory.findAll());
    }
    //
//    @PostMapping("/saveSubject")    @RequestMapping(value="/",method= RequestMethod.GET)
//    public String initPage(Model model){
//        Subject subject=new Subject();
//        model.addAttribute("subject",subject);
//        return "viewsubject";
//    }
//    public String saveSubject(@ModelAttribute("subject") Subject subject){
//        subjectService.saveSubject(subject);
//        return "redirect:/api/subject/";
//    }
    @PostMapping(value="/createsubject")
    public ResponseEntity<ReponseDTO> save(@RequestBody ReponseSubjectDTO dto){
//        Subject createSubject = subjectService.saveSubject(subject);
//        if (createSubject == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//        return ResponseEntity.ok().body(createSubject);
//               }
        Subject subject1=new Subject();
        subject1.setSubject_Id(dto.getSubject_Id());
        subject1.setSubjectName(dto.getSubjectName());
        subject1.setDateCreate(dto.getDateCreate());
        subject1=subjectService.saveSubject(subject1);
        ReponseDTO reponseDTO=new ReponseDTO();
        reponseDTO.setData(subject1);
        reponseDTO.setSuccessCode("create subject success");
        return ResponseEntity.ok().body(reponseDTO);
    }
    @GetMapping(value ="/get/{subject_Id}")
    public ResponseEntity<Subject> getBySubject(@PathVariable(value="subject_Id") Long subject_Id ){
        System.out.println(subject_Id);
        return ResponseEntity.ok().body(subjectService.getSubById(subject_Id));
    }

}

