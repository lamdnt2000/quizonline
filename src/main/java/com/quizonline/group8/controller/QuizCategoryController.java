package com.quizonline.group8.controller;

import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.dto.ResponseQuizCategoryDTO;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.impl.QuizCategoryResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.QuizCategoryResponseModel;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizcategory")
public class QuizCategoryController {
    @Autowired
    SubjectRepository subjectRepository;
    //Them autowired báo lỗi
    private QuizCategoryResponseModelMapper quizCategoryResponseModelMapper;
    @Autowired
    private QuizCategoryService quizCategoryService;
//    @GetMapping("/find")
//    public ResponseEntity<List<QuizCategoryResponseModel>> findBySubject(@RequestParam(name="id") Optional<Long> subjectId){
//        Long id = subjectId.get();
//        List<QuizCategoryDTO> quizCategoryDTOS = quizCategoryService.findQuizCategoryBySubjectId(id);
//        List<QuizCategoryResponseModel> quizCategoryResponseModels = this.quizCategoryResponseModelMapper.toResponseModel(quizCategoryDTOS);
//        return ResponseEntity.ok(quizCategoryResponseModels);
//    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createQuiz(@RequestBody @Valid ResponseQuizCategoryDTO dto){
        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setExam_id(dto.getExam_id());
        quizCategory.setExamTime(dto.getExamTime());
        quizCategory.setNumQuest(dto.getNumQuest());
        quizCategory.setExamName(dto.getExamName());
        quizCategory.setSubject(dto.getSubjectID());
        quizCategory = quizCategoryService.createQuiz(quizCategory);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(quizCategory);
        responseDTO.setSuccessCode("Create quiz category success");
        return ResponseEntity.ok().body(responseDTO);
//        QuizCategory quizCategory1 = quizCategoryService.createQuiz(quizCategory.getExam_id());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("QuizCategory", "/api/QuizCategory" + quizCategory1.getExam_id().toString());
//        return new ResponseEntity<>(quizCategory1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public  ResponseEntity<ResponseDTO> updateQuiz(@Valid @RequestBody ResponseQuizCategoryDTO dto, @PathVariable("id") Long id){
        try{
            QuizCategory quizCategory = new QuizCategory();
            quizCategory.setTimeCreate(dto.getTimeCreate());
            quizCategory.setExamTime(dto.getExamTime());
            quizCategory.setNumQuest(dto.getNumQuest());
            quizCategory.setExamName(dto.getExamName());
            ResponseDTO responseDTO;
            responseDTO = quizCategoryService.updateQuiz(quizCategory,id);
            return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/showQuiz")
    public ResponseEntity<List<QuizCategory>> showQuiz(){
        try {
            List<QuizCategory> quizCategories = quizCategoryService.showQuiz();
            return new ResponseEntity<>(quizCategories, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
