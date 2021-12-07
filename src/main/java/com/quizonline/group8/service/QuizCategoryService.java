package com.quizonline.group8.service;

import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.model.QuizCategory;

import java.util.List;

public interface QuizCategoryService {
    QuizCategory createQuiz(QuizCategoryDTO quizCategoryDTO);
    List<QuizCategory> showQuiz();
    QuizCategory updateQuiz(QuizCategoryDTO quizCategoryDTO);
    QuizCategory findQuizById(Long id);
    List<QuizCategoryDTO> findQuizCategoryBySubjectId(Long id);
    List<QuizCategory> searchQuizCategory(MultiQuerySearchDTO multiQuerySearchDTO);
    Integer countQuizCategory(MultiQuerySearchDTO multiQuerySearchDTO);
}
