package com.quizonline.group8.service;

import com.quizonline.group8.dto.ResponeDTO;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepo questionRepo;

    private ChoiceServiceImpl choiceService;

    @Override
    public List<Question> findByTitle(String title, Long sub_id) {
        return questionRepo.findAllByQuestionTitle(title, sub_id);
    }

    @Override
    public Question createNewQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public ResponeDTO updateQuestion(Long quest_Id, Question question) {
        ResponeDTO responeDTO = new ResponeDTO();
        question = questionRepo.findById(quest_Id).get();
        if (question==null){
            throw new IllegalStateException("Cant not found this question");
        }
        responeDTO.setData(questionRepo.save(question));
        responeDTO.setSuccessCode("Update question sucessful");
        return responeDTO;
    }
}
