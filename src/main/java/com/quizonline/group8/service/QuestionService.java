package com.quizonline.group8.service;

import com.quizonline.group8.mapper.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    public List<QuestionDTO> findQuestionBySubjectId(Long subjectId,int page);
    public List<QuestionDTO> findQuestionRandBySubjectId(Integer numberOfQuest,Long subjectId);
    public Integer checkCorrectAnswert(Long questId, Integer choice);

}
