package com.quizonline.group8.service.impl;

import com.quizonline.group8.common.Constants;
import com.quizonline.group8.dto.MultiQuerySearchDTO;
import com.quizonline.group8.dto.ResponseDTO;
import com.quizonline.group8.mapper.impl.QuestionDTOMapper;
import com.quizonline.group8.model.Question;
import com.quizonline.group8.model.Subject;
import com.quizonline.group8.repository.QuestionRepository;
import com.quizonline.group8.repository.SubjectRepository;
import com.quizonline.group8.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private ChoiceServiceImpl choiceService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionDTOMapper questionDTOMapper;
    @Autowired
    private SubjectRepository subjectRepository;
/*    @Override
    public List<QuestionDTO> findQuestionBySubjectId(Long subjectId, int page) {
        Optional<Subject> subjectRepo = subjectRepository.findById(subjectId);
        Pageable pageable = PageRequest.of(page, Constants.QUESTION_PER_PAGE_IN_QUIZ);
        List<Question> questionList = this.questionRepository.findAllBySubject(subjectRepo.get(),pageable);
        List<QuestionDTO> questionDTOS = this.questionDTOMapper.toDTO(questionList);
        return  questionDTOS;
    }*/

    @Override
    public Integer checkCorrectAnswert(Long questId, Integer choice) {
        return this.questionRepository.countByQuest_IDAAndCorrectAnswer(questId,choice);
    }

    @Override
    public List<Question> searchQuestion(MultiQuerySearchDTO multiQuerySearchDTO) {
        if (multiQuerySearchDTO.getSubject()==null){
            Subject subject = subjectRepository.findById(1L).get();
            multiQuerySearchDTO.setSubject(subject);
        }
        Pageable pageable = PageRequest.of(multiQuerySearchDTO.getPage()-1, Constants.QUESTION_PER_PAGE);
        return this.questionRepository.findByQuestionTitleContainingAndSubjectAndStatus(multiQuerySearchDTO.getTitle(), multiQuerySearchDTO.getSubject(), multiQuerySearchDTO.getStatus(),pageable);
    }

    @Override
    public Integer countSearchQuestion(MultiQuerySearchDTO multiQuerySearchDTO) {
        if (multiQuerySearchDTO.getSubject()==null){
            Subject subject = subjectRepository.findById(1L).get();
            multiQuerySearchDTO.setSubject(subject);
        }
        return this.questionRepository.countByQuestionTitleContainingAndSubjectAndStatus(multiQuerySearchDTO.getTitle(), multiQuerySearchDTO.getSubject(), multiQuerySearchDTO.getStatus());
    }

    @Override
    public Question createNewQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public ResponseDTO updateQuestion(Question question) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (questionRepository.findById(question.getQuest_ID()).get()==null){
            throw new IllegalStateException("Cant not found this question");
        }
        responseDTO.setData(questionRepository.save(question));
        responseDTO.setSuccessCode(Constants.SUCCESS_CODE);
        return responseDTO;
    }

    @Override
    public Question findQuestionById(Long questId) {
        return this.questionRepository.findById(questId).orElseGet(null);
    }

}
