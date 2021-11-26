package com.quizonline.group8.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


   @GetMapping(value="/create")
   public ResponseEntity<QuizResponseModel> createQuiz(@RequestParam(name="id")Optional<Long> id){
       QuizDTO quizDTO = this.quizService.createQuiz(id.get());
       QuizResponseModel quizResponseModel = this.quizResponseModelMapper.toResponseModel(quizDTO);
       return ResponseEntity.ok(quizResponseModel);
   }

    @GetMapping(value="/question")
    public ResponseEntity<List<QuizQuestionListResponseModel>> showDetails(@RequestParam(name="examId") Optional<Long> examId, @RequestParam(name="page") Optional<Integer> page){
        List<QuizQuestionListDTO> quizQuestionListDTOS = this.quizQuestionListService.findByExamId(examId.get(),page.get());
        List<QuizQuestionListResponseModel> quizQuestionListResponseModels = this.quizQuestionListResponseModelMapper.toResponseModel(quizQuestionListDTOS);
        return ResponseEntity.ok(quizQuestionListResponseModels);
    }

    @GetMapping(value="/quizDetail")
    public ResponseEntity<QuizResponseModel> showQuizDetails(@RequestParam(name="id") Optional<Long> examId) throws NotFoundException {
        QuizDTO quizDTO = this.quizService.findQuiz(examId.get());
        QuizResponseModel quizResponseModel = this.quizResponseModelMapper.toResponseModel(quizDTO);
        return ResponseEntity.ok(quizResponseModel);
    }

    @PostMapping(value="/submitQuiz")
    public ResponseEntity<QuizResponseModel> submitQuizResult(@RequestBody MultiValueMap<String, String> formData, HttpServletRequest request) throws NotFoundException {
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
        QuizResponseModel model = this.quizResponseModelMapper.toResponseModel(quiz);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/history")
    public ResponseEntity<List<QuizResponseModel>> showHistory(){
        List<QuizDTO> quizDTOS = this.quizService.showQuizHistory();
        List<QuizResponseModel> quizResponseModels = this.quizResponseModelMapper.toResponseModel(quizDTOS);
        return ResponseEntity.ok(quizResponseModels);
    }
}
