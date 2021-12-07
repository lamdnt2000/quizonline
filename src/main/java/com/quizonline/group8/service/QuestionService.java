package com.quizonline.group8.service;

import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.model.Question;

import java.util.List;

public interface QuestionService {
    public Integer checkCorrectAnswert(Long questId, Integer choice);
    List<Question> searchQuestion(MultiQuerySearchDTO multiQuerySearchDTO);
    Integer countSearchQuestion(MultiQuerySearchDTO multiQuerySearchDTO);
    Question createNewQuestion(Question question);
    ResponseDTO updateQuestion(Question question);
    public Question findQuestionById(Long questId);
}
