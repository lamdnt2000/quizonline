package com.quizonline.group8.controller;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.impl.QuizCategoryResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.QuizCategoryResponseModel;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.service.QuizCategoryService;
import com.quizonline.group8.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizcategory")
public class QuizCategoryController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    private QuizCategoryResponseModelMapper quizCategoryResponseModelMapper;
    @Autowired
    private QuizCategoryService quizCategoryService;

    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<QuizCategory>> getByTitle(@Valid MultiQuerySearchDTO multiQuerySearchDTO) {
        return ResponseEntity.ok().body(quizCategoryService.searchQuizCategory(multiQuerySearchDTO));
    }

    @GetMapping(value = "/count", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Integer> getTotalQuestion(@Valid MultiQuerySearchDTO multiQuerySearchDTO) {
        return ResponseEntity.ok().body(quizCategoryService.countQuizCategory(multiQuerySearchDTO));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createQuiz(@Valid @RequestBody QuizCategoryDTO quizCategoryDTO) {
        QuizCategory quizCategory = this.quizCategoryService.createQuiz(quizCategoryDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(quizCategory);
        responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateQuiz(@Valid @RequestBody QuizCategoryDTO quizCategoryDTO) {
        try {
            QuizCategory quizCategory = this.quizCategoryService.updateQuiz(quizCategoryDTO);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setData(quizCategory);
            responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/showQuiz")
    public ResponseEntity<List<QuizCategory>> showQuiz() {
        try {
            List<QuizCategory> quizCategories = quizCategoryService.showQuiz();
            return new ResponseEntity<>(quizCategories, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<List<QuizCategoryResponseModel>> findBySubject(@RequestParam(name = "id") Optional<Long> subjectId) {
        Long id = subjectId.get();
        List<QuizCategoryDTO> quizCategoryDTOS = quizCategoryService.findQuizCategoryBySubjectId(id);
        List<QuizCategoryResponseModel> quizCategoryResponseModels = this.quizCategoryResponseModelMapper.toResponseModel(quizCategoryDTOS);
        return ResponseEntity.ok(quizCategoryResponseModels);
    }
}
