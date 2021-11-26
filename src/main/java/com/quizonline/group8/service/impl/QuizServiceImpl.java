package com.quizonline.group8.service.impl;

import com.quizonline.group8.mapper.dto.QuizDTO;
import com.quizonline.group8.mapper.impl.QuizDTOMapper;
import com.quizonline.group8.model.*;
import com.quizonline.group8.repository.QuestionRepository;
import com.quizonline.group8.repository.QuizCategoryRepository;
import com.quizonline.group8.repository.QuizRepository;
import com.quizonline.group8.service.QuizService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.Timestamp;
import java.util.*;

@Service
@SessionScope
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizCategoryRepository quizCategoryRepository;
    @Autowired
    private QuizQuestionListServiceImpl quizQuestionListService;
    @Autowired
    private QuizDTOMapper quizDTOMapper;
    @Override
    public QuizDTO createQuiz(Long examId) {
        QuizCategory quizCategory = this.quizCategoryRepository.findById(examId).get();
        if (Objects.nonNull(quizCategory)){
            Quiz quiz = new Quiz();
            quiz.setDateCreate(new Timestamp(new Date().getTime()));
            quiz.setMember(null);
            quiz.setQuizTime(quizCategory.getExamTime());
            quiz.setStatus(0);
            quiz.setQuizcategory(quizCategory);
            quiz = quizRepository.save(quiz);
            List<Question> questionList = this.questionRepository.findByTopOrderByRand(quizCategory.getNumQuest(),quizCategory.getSubject().getSubject_Id());
            for (Question question: questionList){
                QuizQuestionList quizQuestionList = new QuizQuestionList();

                QuizQuestionListID quizQuestionListID = new QuizQuestionListID();
                quizQuestionListID.setQuest_id(question.getQuest_ID());
                quizQuestionListID.setQuiz_id(quiz.getQuiz_Id());
                quizQuestionList.setQuizQuestionListID(quizQuestionListID);
                this.quizQuestionListService.saveQuizQuestion(quizQuestionList);
            }
            return this.quizDTOMapper.toDTO(quiz);
        }
        else{
            throw new IllegalArgumentException("Invalid exam");
        }
    }

    @Override
    public boolean finishQuiz() {
        return false;
    }

    @Override
    public QuizDTO findQuiz(Long examId) throws NotFoundException {
        Optional<Quiz> quiz = Optional.ofNullable(this.quizRepository.findById(examId).orElseGet(null));
        if (Objects.nonNull(quiz)){
            QuizDTO quizDTO = this.quizDTOMapper.toDTO(quiz.get());
            return quizDTO;
        }
        else{
            throw new NotFoundException("Quiz id not found");
        }
    }

    @Override
    public void updateQuiz(QuizDTO quizDTO) {
        Quiz quiz = this.quizDTOMapper.toEntity(quizDTO);
        this.quizRepository.saveAndFlush(quiz);
    }

    @Override
    public List<QuizDTO> showQuizHistory() {
        List<Quiz> quizzes = this.quizRepository.findAll();
        List<QuizDTO> quizDTOS = this.quizDTOMapper.toDTO(quizzes);
        return quizDTOS;
    }


}
