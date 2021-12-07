package com.quizonline.group8.controller;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.mapper.dto.QuizDTO;
import com.quizonline.group8.mapper.dto.QuizQuestionListDTO;
import com.quizonline.group8.mapper.impl.QuizQuestionListResponseModelMapper;
import com.quizonline.group8.mapper.impl.QuizResponseModelMapper;
import com.quizonline.group8.mapper.resposemodel.QuizQuestionListResponseModel;
import com.quizonline.group8.mapper.resposemodel.QuizResponseModel;
import com.quizonline.group8.model.QuizQuestionList;
import com.quizonline.group8.service.QuestionService;
import com.quizonline.group8.service.QuizQuestionListService;
import com.quizonline.group8.service.QuizService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.quizonline.group8.common.ParameterConvert.stringToNumber;

@RestController
@RequestMapping(value="/api/quiz")
public class QuizController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizResponseModelMapper quizResponseModelMapper;
    @Autowired
    private QuizQuestionListResponseModelMapper quizQuestionListResponseModelMapper;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizQuestionListService quizQuestionListService;


   @PostMapping(value="/create")
   public ResponseEntity<ResponseDTO> createQuiz(@RequestParam(name="id")Optional<Long> id){
       ResponseDTO responseDTO = new ResponseDTO();
       try {
           QuizDTO quizDTO = this.quizService.createQuiz(id.get());
           responseDTO.setData(quizDTO.getQuiz_Id());
           responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
       }
       catch (Exception e){
           responseDTO.setData("Create new Quiz failed");
           responseDTO.setSuccessCode(Constants.FAIL_CODE);
       }
       finally {
           return ResponseEntity.ok(responseDTO);
       }

   }

    @GetMapping(value="/question")
    public ResponseEntity<ResponseDTO> showDetails(@RequestParam(name="examId") Optional<Long> examId, @RequestParam(name="page") Optional<Integer> page){
        List<QuizQuestionListDTO> quizQuestionListDTOS = this.quizQuestionListService.findByExamId(examId.get(),page.get());
        List<QuizQuestionListResponseModel> quizQuestionListResponseModels = this.quizQuestionListResponseModelMapper.toResponseModel(quizQuestionListDTOS);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(quizQuestionListResponseModels);
        responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value="/quizDetail")
    public ResponseEntity<ResponseDTO> showQuizDetails(@RequestParam(name="id") Optional<Long> examId) throws NotFoundException {
        QuizDTO quizDTO = this.quizService.findQuiz(examId.get());
        QuizResponseModel quizResponseModel = this.quizResponseModelMapper.toResponseModel(quizDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(quizResponseModel);
        responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value="/submitQuiz")
    public ResponseEntity<ResponseDTO> submitQuizResult(@RequestBody MultiValueMap<String, String> formData) throws NotFoundException {
        Long id = stringToNumber(formData.get("id"),Long::parseLong);
        QuizDTO quiz = this.quizService.findQuiz(id);
        Integer totalCorrect = 0;
        if (Objects.nonNull(quiz) && quiz.getStatus()==0) {
            quiz.setDateSubmit(new Timestamp(new Date().getTime()));
            if (formData.size()>3) {
                for (String questStr : formData.get("questIds[]")) {
                    Long questId = Long.parseLong(questStr);
                    int memResult = stringToNumber(formData.get(questStr), Integer::parseInt);
                    QuizQuestionList quizQuestionList = quizQuestionListService.findByKey(id, questId);
                    quizQuestionList.setMemResult(memResult);
                    this.quizQuestionListService.saveQuizQuestion(quizQuestionList);
                    totalCorrect += this.questionService.checkCorrectAnswert(questId, memResult);
                }
            }
            float total = (float)Math.round((float)totalCorrect/quiz.getQuizcategory().getNumQuest()*1000)/1000 * 10;
            quiz.setTotal(total);
            quiz.setStatus(1);
            this.quizService.updateQuiz(quiz);

        }
        QuizResponseModel quizResponseModel = this.quizResponseModelMapper.toResponseModel(quiz);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(quizResponseModel);
        responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/history")
    public ResponseEntity<List<QuizResponseModel>> showHistory(){
        List<QuizDTO> quizDTOS = this.quizService.showQuizHistory();
        List<QuizResponseModel> quizResponseModels = this.quizResponseModelMapper.toResponseModel(quizDTOS);
        return ResponseEntity.ok(quizResponseModels);
    }

    @GetMapping(value="/search",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<QuizResponseModel>> searchHistory(MultiQuerySearchDTO multiQuerySearchDTO){
        List<QuizDTO> quizDTOS = this.quizService.searchHistory(multiQuerySearchDTO);
        List<QuizResponseModel> quizResponseModels = this.quizResponseModelMapper.toResponseModel(quizDTOS);
        return ResponseEntity.ok(quizResponseModels);
    }
}
