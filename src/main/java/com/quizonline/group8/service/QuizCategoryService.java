package com.quizonline.group8.service;

import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.model.QuizCategory;

import java.util.List;

public interface QuizCategoryService {
    QuizCategory createQuiz(QuizCategory quizCategory);
    List<QuizCategory> showQuiz();
    ResponseDTO updateQuiz(QuizCategory quizCategory, Long id);
    QuizCategory findQuizById(Long id);
//    List<QuizCategoryDTO> findQuizCategoryBySubjectId(Long id);
}
