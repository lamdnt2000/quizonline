package com.quizonline.group8.service.impl;

import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.impl.QuizCategoryMapper;
import com.quizonline.group8.model.QuizCategory;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.QuizCategoryRepository;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {
    @Autowired
    private SubjectRepository subjectRepository;
//    @Autowired
//    private QuizCategoryMapper quizCategoryMapper;
    @Autowired
    private QuizCategoryRepository quizCategoryRepository;
//    @Override
//    public List<QuizCategoryDTO> findQuizCategoryBySubjectId(Long id) {
//        Subject subject = this.subjectRepository.findById(id).get();
//        List<QuizCategory> quizCategoryList = this.quizCategoryRepository.findBySubject(subject);
//        List<QuizCategoryDTO> quizCategoryDTOS = quizCategoryMapper.toDTO(quizCategoryList);
//        return quizCategoryDTOS;
//    }
    @Override
    public QuizCategory createQuiz(QuizCategory quizCategory) {
        this.quizCategoryRepository.save(quizCategory);
        return quizCategory;
    }

    @Override
    public List<QuizCategory> showQuiz() {
        List<QuizCategory> quizCategories = new ArrayList<>();
        quizCategories = this.quizCategoryRepository.findAll();
        return quizCategories;
    }

    @Override
    public ResponseDTO updateQuiz(QuizCategory quizCategory, Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        quizCategory = quizCategoryRepository.findById(id).get();
        if(quizCategory == null){
            throw new IllegalStateException("Nothing in quiz category");
        }
        responseDTO.setData(quizCategoryRepository.save(quizCategory));
        responseDTO.setSuccessCode("Update quiz category successful");
        return responseDTO;
//        QuizCategory quizCategoryFromDB = quizCategoryRepository.findById(id).get();
//        System.out.println(quizCategoryFromDB.toString());
//        quizCategoryFromDB.setNumQuest(quizCategory.getNumQuest());
//        quizCategoryFromDB.setExamTime(quizCategory.getExamTime());
//        quizCategoryFromDB.setExamName(quizCategory.getExamName());
//        quizCategoryFromDB.setTimeCreate(quizCategory.getTimeCreate());
//        quizCategoryFromDB.setSubjectID(quizCategory.getSubjectID());
//        quizCategoryRepository.save(quizCategoryFromDB);
    }

    @Override
    public QuizCategory findQuizById(Long id) {
        return quizCategoryRepository.findById(id).get();
    }
}
