package com.quizonline.group8.controller;

import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.impl.QuizCategoryResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.QuizCategoryResponseModel;
import com.quizonline.group8.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizcategory")
public class QuizCategoryController {
    @Autowired
    private QuizCategoryResponseModelMapper quizCategoryResponseModelMapper;
    @Autowired
    private QuizCategoryService quizCategoryService;
    @GetMapping("/find")
    public ResponseEntity<List<QuizCategoryResponseModel>> findBySubject(@RequestParam(name="id") Optional<Long> subjectId){
        Long id = subjectId.get();
        List<QuizCategoryDTO> quizCategoryDTOS = quizCategoryService.findQuizCategoryBySubjectId(id);
        List<QuizCategoryResponseModel> quizCategoryResponseModels = this.quizCategoryResponseModelMapper.toResponseModel(quizCategoryDTOS);
        return ResponseEntity.ok(quizCategoryResponseModels);
    }
}
