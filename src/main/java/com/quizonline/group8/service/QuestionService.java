package com.quizonline.group8.service;

import com.quizonline.group8.dto.ResponeDTO;
import com.quizonline.group8.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findByTitle(String title, Long sub_id);
    Question createNewQuestion(Question question);
    ResponeDTO updateQuestion(Long quest_Id, Question question);
}
