package com.quizonline.group8.mapper.dto;

import com.quizonline.group8.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseQuestionDTO implements Serializable {
    private Long quest_ID;
    private String questionTitle;
    private Integer correctAnswer;
    private Integer status=0;
    private Timestamp dateCreate;
    private Subject subject;
    private List<ResponeChoiceDTO> choice;
    private Boolean isDelete=false;
}
