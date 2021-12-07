package com.quizonline.group8.controller;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.mapper.dto.ResponseQuestionDTO;
import com.quizonline.group8.mapper.impl.ResponseChoiceDTOMapper;
import com.quizonline.group8.mapper.impl.ResponseQuestionDTOMapper;
import com.quizonline.group8.model.Choise;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.service.impl.ChoiceServiceImpl;
import com.quizonline.group8.service.impl.QuestionServiceImpl;
import com.quizonline.group8.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    private ResponseQuestionDTOMapper responseQuestionDTOMapper;

    @Autowired
    private ResponseChoiceDTOMapper responseChoiceDTOMapper;

    @GetMapping(value="/search",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<Question>> getByTitle(@Valid MultiQuerySearchDTO multiQuerySearchDTO){
        return ResponseEntity.ok().body(questionService.searchQuestion(multiQuerySearchDTO));
    }

    @GetMapping(value="/count",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Integer> getTotalQuestion(@Valid MultiQuerySearchDTO multiQuerySearchDTO){
        return ResponseEntity.ok().body(questionService.countSearchQuestion(multiQuerySearchDTO));
    }

    @PostMapping(value="/create",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResponseDTO> createQuestion(@Valid ResponseQuestionDTO dto){
        try {

            // check sub_id
            Subject check = dto.getSubject();
            if (check == null) {
                throw new IllegalStateException("Can not find any subject");
            } else {

                Question question = responseQuestionDTOMapper.toEntity(dto);
                question.setStatus(1);
                question.setDateCreate(TimeUtils.getCurrentTime());

                question = questionService.createNewQuestion(question);
                int count =0;
                for (Choise option : question.getChoice()) {
                    option.setAnswernumber(++count);
                    option.setQuestion(question);
                    choiceService.createAnswer(option);
                }
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setData(question);
                responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
                return ResponseEntity.ok().body(responseDTO);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping(value="/update",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResponseDTO> updateQuestion(@Valid ResponseQuestionDTO dto,@RequestParam(name="_isDelete") String status){
        try {

            dto.setStatus("on".equals(status)?1:0);
            ResponseDTO responseDTO = new ResponseDTO();
            Question question = this.responseQuestionDTOMapper.toEntity(dto);
            Question oldQuestion = questionService.findQuestionById(question.getQuest_ID());
            if (oldQuestion!=null){
                question.setDateUpdate(TimeUtils.getCurrentTime());
                question.setDateCreate(oldQuestion.getDateCreate());
                int count =0;
                for (Choise option : question.getChoice()) {
                    option.setAnswernumber(++count);
                    option.setQuestion(question);
                    choiceService.createAnswer(option);
                }
                responseDTO = questionService.updateQuestion(question);
            }
            else{
                responseDTO.setErrorCode(Constants.FAIL_CODE);
                responseDTO.setData("Question not found");
            }
            return  ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value="/find")
    public ResponseEntity<Question> findQuestionById(@RequestParam(name="id") Optional<Long> questId){
        return ResponseEntity.ok(this.questionService.findQuestionById(questId.get()));
    }
}
