package com.quizonline.group8.service;

import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.mapper.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    public List<QuestionDTO> findQuestionBySubjectId(Long subjectId,int page);
    public List<QuestionDTO> findQuestionRandBySubjectId(Integer numberOfQuest,Long subjectId);
    public Integer checkCorrectAnswert(Long questId, Integer choice);
    List<Question> findByTitle(String title, Long sub_id);
    Question createNewQuestion(Question question);
    ResponseDTO updateQuestion(Long quest_Id, Question question);
}
