package com.quizonline.group8.service.impl;

import com.quizonline.group8.mapper.dto.QuizDTO;
import com.quizonline.group8.mapper.impl.QuizDTOMapper;
import com.quizonline.group8.model.*;
import com.quizonline.group8.repository.MemberRepository;
import com.quizonline.group8.repository.QuestionRepository;
import com.quizonline.group8.repository.QuizCategoryRepository;
import com.quizonline.group8.repository.QuizRepository;
import com.quizonline.group8.service.QuizService;
import com.quizonline.group8.utils.UserSecurityUtil;
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
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public QuizDTO createQuiz(Long examId) {
        QuizCategory quizCategory = this.quizCategoryRepository.findById(examId).get();
        Member member = UserSecurityUtil.getCurrentUser();
        if (Objects.nonNull(quizCategory)){
            Quiz quiz = new Quiz();
            quiz.setDateCreate(new Timestamp(new Date().getTime()));
            quiz.setMember(member);
            quiz.setQuizTime(quizCategory.getExamTime());
            quiz.setStatus(0);
            quiz.setTotal(0F);
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
        Member member = UserSecurityUtil.getCurrentUser();
        int status = 0;
        Quiz quiz = this.quizRepository.findByIdAndMember(examId,member,status);
        if (Objects.nonNull(quiz)){
            QuizDTO quizDTO = this.quizDTOMapper.toDTO(quiz);
            return quizDTO;
        }
        else{
            throw new NotFoundException("Quiz id not available");
        }
    }

    @Override
    public void updateQuiz(QuizDTO quizDTO) {
        Quiz quiz = this.quizDTOMapper.toEntity(quizDTO);
        this.quizRepository.saveAndFlush(quiz);
    }

    @Override
    public List<QuizDTO> showQuizHistory() {
        Member member = UserSecurityUtil.getCurrentUser();
        List<Quiz> quizzes = this.quizRepository.findByMember(member);
        List<QuizDTO> quizDTOS = this.quizDTOMapper.toDTO(quizzes);
        return quizDTOS;
    }


}
