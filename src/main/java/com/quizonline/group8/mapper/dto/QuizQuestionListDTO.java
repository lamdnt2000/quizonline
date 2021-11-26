package com.quizonline.group8.mapper.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuizQuestionListDTO implements Serializable {
    private Integer memResult;
    private QuestionDTO question;
}
