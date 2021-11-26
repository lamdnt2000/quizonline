package com.quizonline.group8.service.impl;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.mapper.dto.QuizQuestionListDTO;
import com.quizonline.group8.mapper.impl.QuizQuestionListDTOMapper;
import com.quizonline.group8.model.QuizQuestionList;
import com.quizonline.group8.model.QuizQuestionListID;
import com.quizonline.group8.repository.QuizQuestionListRepository;
import com.quizonline.group8.service.QuizQuestionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuizQuestionListServiceImpl implements QuizQuestionListService {
    @Autowired
    private QuizQuestionListRepository questionListRepository;
    @Autowired
    private QuizQuestionListDTOMapper quizQuestionListDTOMapper;
    @Override
    public QuizQuestionList saveQuizQuestion(QuizQuestionList quizQuestionList) {

        return this.questionListRepository.save(quizQuestionList);
    }

    @Override
    public List<QuizQuestionListDTO> findByExamId(Long examId,int page) {
        Pageable pageable = PageRequest.of(page, Constants.QUESTION_PER_PAGE_IN_QUIZ);
        List<QuizQuestionList> quizQuestionLists = this.questionListRepository.findByExamId(examId,pageable);
        List<QuizQuestionListDTO> quizQuestionListDTOS = this.quizQuestionListDTOMapper.toDTO(quizQuestionLists);
        return quizQuestionListDTOS;
    }

    @Override
    public QuizQuestionList findByKey(Long examId, Long questId) {
        QuizQuestionListID key = new QuizQuestionListID();
        key.setQuiz_id(examId);
        key.setQuest_id(questId);
        return this.questionListRepository.findById(key).orElseGet(null);
    }

    @Override
    public void update(QuizQuestionList quizQuestionList) {
        if (this.questionListRepository.findById(quizQuestionList.getQuizQuestionListID()).orElseThrow(EntityNotFoundException::new)!=null){
            this.questionListRepository.saveAndFlush(quizQuestionList);
        }
    }
}
