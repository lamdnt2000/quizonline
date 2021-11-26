package com.quizonline.group8.service;

import com.quizonline.group8.mapper.dto.QuizCategoryDTO;

import java.util.List;

public interface QuizCategoryService {
    public List<QuizCategoryDTO> findQuizCategoryBySubjectId(Long id);
}
