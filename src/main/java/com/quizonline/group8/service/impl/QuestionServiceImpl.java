package com.quizonline.group8.service.impl;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.mapper.dto.QuestionDTO;
import com.quizonline.group8.mapper.impl.QuestionDTOMapper;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.QuestionRepository;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionDTOMapper questionDTOMapper;
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public List<QuestionDTO> findQuestionBySubjectId(Long subjectId, int page) {
        Optional<Subject> subjectRepo = subjectRepository.findById(subjectId);
        Pageable pageable = PageRequest.of(page, Constants.QUESTION_PER_PAGE_IN_QUIZ);
        List<Question> questionList = this.questionRepository.findAllBySubject(subjectRepo.get(),pageable);
        List<QuestionDTO> questionDTOS = this.questionDTOMapper.toDTO(questionList);
        return  questionDTOS;
    }

    @Override
    public List<QuestionDTO> findQuestionRandBySubjectId(Integer numberOfQuest,Long subjectId) {
        List<Question> questionList = this.questionRepository.findByTopOrderByRand(numberOfQuest, subjectId);
        List<QuestionDTO> questionDTOS = this.questionDTOMapper.toDTO(questionList);
        return  questionDTOS;
    }

    @Override
    public Integer checkCorrectAnswert(Long questId, Integer choice) {
        return this.questionRepository.countByQuest_IDAAndCorrectAnswer(questId,choice);
    }


}