package com.quizonline.group8.service.impl;

import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.impl.QuizCategoryMapper;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.QuizCategoryRepository;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private QuizCategoryMapper quizCategoryMapper;
    @Autowired
    private QuizCategoryRepository quizCategoryRepository;
    @Override
    public List<QuizCategoryDTO> findQuizCategoryBySubjectId(Long id) {
        Subject subject = this.subjectRepository.findById(id).get();
        List<QuizCategory> quizCategoryList = this.quizCategoryRepository.findBySubject(subject);
        List<QuizCategoryDTO> quizCategoryDTOS = quizCategoryMapper.toDTO(quizCategoryList);
        return quizCategoryDTOS;
    }
}
