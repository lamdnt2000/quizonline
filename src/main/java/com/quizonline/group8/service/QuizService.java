package com.quizonline.group8.service;



import com.quizonline.group8.dto.ResponesDto;
import com.quizonline.group8.model.Quiz;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;

import java.util.List;

public interface QuizService {
    public List<Quiz> findlistAll();
//    QuizCategory createQuizCategory(QuizCategory quizCate);
//    ResponesDto updateQuizCategory(Long exam_id, QuizCategory quizCategory);
}
