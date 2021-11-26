package com.quizonline.group8.controller;

import com.quizonline.group8.dto.ResponeQuizCategoryDTO;
import com.quizonline.group8.dto.ResponesDto;
import com.quizonline.group8.model.*;
import com.quizonline.group8.repository.ViewCategoryRepo;
import com.quizonline.group8.service.QuizService;
import com.quizonline.group8.service.Subservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class  BaseController {
    @Autowired
    private Subservice subservice;
    @Autowired
    private ViewCategoryRepo categoryRepo;
    @Autowired
    private QuizService quizService;
//    ---show all subject--
    @GetMapping("/subAll")
    public ResponseEntity<List<Subject>> findCities() {
        List<Subject> subjects = subservice.findAll();
        return ResponseEntity.ok().body(subjects);
    }
    //    ---show subject by subjectId--
    @GetMapping("/subAll/{subjectId}")
    public ResponseEntity<Optional<Subject>> findCity(@PathVariable Long subjectId) {

        Optional<Subject> subject = subservice.findById(subjectId);
        return ResponseEntity.ok().body(subject);
    }
    //    ---show QuizCategory by subjectId--
    @GetMapping("/Category/{subjectId}")
    public ResponseEntity<List<QuizCategory>> GetCategory(@PathVariable Long subjectId) {
        List<QuizCategory> category = categoryRepo.findBySubject_id(subjectId);
        return ResponseEntity.ok().body(category);
    }
//---show list quiz-----
    @GetMapping("/listquiz")
    public ResponseEntity<List<Quiz>> GetHistory() {
        List<Quiz> quizs = quizService.findlistAll();
        return ResponseEntity.ok().body(quizs);
    }

    @PostMapping(value="/createsubject")
    public ResponseEntity<Subject> save(@RequestBody Subject subjects) {
        Subject createSubject = subservice.createSubject(subjects);
        if (createSubject == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(createSubject);
        }
    }


//    @PostMapping("/create/{subjectId}")
//    public ResponseEntity<ResponesDto> createQuestion(@RequestBody ResponeQuizCategoryDTO dto){
//        try {
//            // check subjectId
//            Subject check = dto.getSubject_id();
//            if (check == null) {
//                throw new IllegalStateException("Can not find any subject");
//            }
//                //create new quizCategory
//            QuizCategory quizCategory = new QuizCategory();
//            quizCategory.setTimeCreate(dto.getTimeCreate());
//            quizCategory.setExamName(dto.getExamName());
//            quizCategory.setNumQuest(dto.getNumQuest());
//            quizCategory.setSubject(dto.getSubject_id());
//            quizCategory =quizService.createQuizCategory(quizCategory);
//
//                ResponesDto responeDTO = new ResponesDto();
//                responeDTO.setData(quizCategory);
//                responeDTO.setSuccessCode("QuizCategory create Successful!");
//                return ResponseEntity.ok().body(responeDTO);
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
////    @PutMapping("/update/{exam_id}")
////    public ResponseEntity<ResponesDto> updateQuestion(@PathVariable("exam_id") Long quest_id,
////                                                     @RequestBody ResponeQuizCategoryDTO dto){
////        try {
////            QuizCategory quizCategory = new QuizCategory();
////            quizCategory.setTimeCreate(dto.getTimeCreate());
////            quizCategory.setExamName(dto.getExamName());
////            quizCategory.setNumQuest(dto.getNumQuest());
////            quizCategory.setExamTime(dto.getExamTime());
////            quizCategory.setSubjectID(dto.getSubjectID());
////            quizCategory.setSubject(dto.getSubject_id());
////            ResponesDto responeDTO = new ResponesDto()
////            responeDTO = quizService.updateQuizCategory(exam_id,quizCategory);
////            return  ResponseEntity.ok().body(responeDTO);
////        }catch (Exception e){
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////        }
////    }
}
