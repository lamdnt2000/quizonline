package com.quizonline.group8.service;

import com.quizonline.group8.dto.ResponesDto;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.Quiz;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    private QuizRepository QuizRepo;
    @Override
    public List<Quiz> findlistAll() {
        List<Quiz> Quizs = QuizRepo.findAll();
        return Quizs;
    }
//
//    @Override
//    public QuizCategory createNewQuizCategory(QuizCategory quizCate) {
//           return QuizRepo.save(quizCate);
//    }

//    @Override
//    public ResponesDto updateQuizCategory(Long , QuizCategory quizCategory) {
//        ResponesDto responeDTO = new ResponesDto();
//        quizCategory = QuizRepo.findById(exam_id)
//        if (quizCategory==null){
////            throw new IllegalStateException("Cant not found this quizCategory");
////        }
////        responeDTO.setData(QuizRepo.save(quizCategory));
////        responeDTO.setSuccessCode("Update QuizCategory sucessful");
////        return responeDTO;
////
////    }
}
