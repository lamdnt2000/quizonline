package com.quizonline.group8.service;

import com.quizonline.group8.mapper.dto.QuizQuestionListDTO;
import com.quizonline.group8.model.QuizQuestionList;

import java.util.List;

public interface QuizQuestionListService {
    public QuizQuestionList saveQuizQuestion(QuizQuestionList quizQuestionList);
    public List<QuizQuestionListDTO> findByExamId(Long examId, int page);
    public QuizQuestionList findByKey(Long examId, Long questId);
    public void update(QuizQuestionList quizQuestionList);
}
