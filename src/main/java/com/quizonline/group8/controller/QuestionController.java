package com.quizonline.group8.controller;

import com.quizonline.group8.dto.ResponeChoiceDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.dto.ResponeQuestionDTO;
import com.quizonline.group8.model.Choise;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.SubjectRepo;
import com.quizonline.group8.service.impl.ChoiceServiceImpl;
import com.quizonline.group8.service.impl.QuestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/question")
@Controller
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private ChoiceServiceImpl choiceService;

    @Autowired
    private SubjectRepo subjectRepo;

    @GetMapping("/get/{title}/{sub_ID}")
    public ResponseEntity<List<Question>> getByTitle(@PathVariable("title") String title,
                                                     @PathVariable("sub_ID") Long sub_ID){

        return ResponseEntity.ok().body(questionService.findByTitle(title,sub_ID));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createQuestion(@RequestBody ResponeQuestionDTO dto){
        try {
            // check sub_id
            Subject check = dto.getSubject_id();
            if (check == null) {
                throw new IllegalStateException("Can not find any subject");
            } else {
                List<ResponeChoiceDTO> choiseList = dto.getChoiceDTOS();   // lay list choice trong questDTo tra ve tu front end
                List<Choise> options = new ArrayList<>(); // create new choices
                for (int i = 0; i < 4; i++) {
                    Choise choise = new Choise();
                    choise.setAnswer(choiseList.get(i).getAnswer());
                    choise.setAnswernumber(i + 1);
                    options.add(choise);
                }
                //create new question
                Question question = new Question();
                question.setQuestionTitle(dto.getQuestionTitle());
                question.setCorrectAnswer(dto.getCorrectAnswer());
                question.setStatus(dto.getStatus());
                question.setSubject(dto.getSubject_id());
                question = questionService.createNewQuestion(question);
                for (Choise option : options) {
                    option.setQuestion(question);
                    choiceService.createAnswer(option);
                }
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setData(question);
                responseDTO.setSuccessCode("Question create Successful!");
                return ResponseEntity.ok().body(responseDTO);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping("/update/{quest_id}")
    public ResponseEntity<ResponseDTO> updateQuestion(@PathVariable("quest_id") Long quest_id,
                                                      @RequestBody ResponeQuestionDTO dto){
        try {
            Question question = new Question();
            question.setQuestionTitle(dto.getQuestionTitle());
            question.setCorrectAnswer(dto.getCorrectAnswer());
            question.setStatus(dto.getStatus());
            question.setDateUpdate(dto.getDateUpdate());
            question.setUserUpdate(dto.getUserUpdate());
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO = questionService.updateQuestion( quest_id,question);
            return  ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
