package com.quizonline.group8.service.impl;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.impl.QuizCategoryMapper;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.QuizCategoryRepository;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuizCategoryService;
import com.quizonline.group8.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<QuizCategory> searchQuizCategory(MultiQuerySearchDTO multiQuerySearchDTO) {
        if (multiQuerySearchDTO.getSubject()==null){
            Subject subject = subjectRepository.findById(1L).get();
            multiQuerySearchDTO.setSubject(subject);
        }
        Pageable pageable = PageRequest.of(multiQuerySearchDTO.getPage()-1, Constants.QUIZ_CATEGORY_PER_PAGE);
        return this.quizCategoryRepository.findByExamNameContainingAndSubject(multiQuerySearchDTO.getTitle(), multiQuerySearchDTO.getSubject(),pageable);

    }

    @Override
    public Integer countQuizCategory(MultiQuerySearchDTO multiQuerySearchDTO) {
        if (multiQuerySearchDTO.getSubject()==null){
            Subject subject = subjectRepository.findById(1L).get();
            multiQuerySearchDTO.setSubject(subject);
        }
        return this.quizCategoryRepository.countByExamNameContainingAndSubject(multiQuerySearchDTO.getTitle(),multiQuerySearchDTO.getSubject());
    }

    @Override
    public QuizCategory createQuiz(QuizCategoryDTO quizCategoryDTO) {
        QuizCategory quizCategory = quizCategoryMapper.toEntity(quizCategoryDTO);
        quizCategory.setTimeCreate(TimeUtils.getCurrentTime());
        return this.quizCategoryRepository.save(quizCategory);
    }

    @Override
    public List<QuizCategory> showQuiz() {
        return null;
    }

    @Override
    public QuizCategory updateQuiz(QuizCategoryDTO quizCategoryDTO) {
        QuizCategory quizCategory = quizCategoryMapper.toEntity(quizCategoryDTO);
        QuizCategory oldQuizCategory = this.quizCategoryRepository.findById(quizCategory.getExam_id()).get();
        if (Objects.nonNull(oldQuizCategory)){
            quizCategory.setTimeCreate(oldQuizCategory.getTimeCreate());
            return this.quizCategoryRepository.save(quizCategory);
        }
        else{
            throw new EntityNotFoundException("Quiz Category not found");
        }

    }

    @Override
    public QuizCategory findQuizById(Long id) {
        return this.quizCategoryRepository.findById(id).get();
    }
}
