package com.quizonline.group8.service;

import com.quizonline.group8.mapper.dto.QuizDTO;
import javassist.NotFoundException;

import java.util.List;

public interface QuizService {
    public QuizDTO createQuiz(Long examId);
    public boolean finishQuiz();
    public QuizDTO findQuiz(Long examId) throws NotFoundException;
    public void updateQuiz(QuizDTO quizDTO);
    public List<QuizDTO> showQuizHistory();
}
