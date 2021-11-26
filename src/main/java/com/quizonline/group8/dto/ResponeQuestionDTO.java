package com.quizonline.group8.dto;

import com.quizonline.group8.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResponeQuestionDTO  {

    private Long quest_ID;
    private String questionTitle;
    private Integer correctAnswer;
    private Integer status;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
    private String userCreate;
    private String userUpdate;
    private Subject subject_id;
    private List<ResponeChoiceDTO> choiceDTOS;

}
