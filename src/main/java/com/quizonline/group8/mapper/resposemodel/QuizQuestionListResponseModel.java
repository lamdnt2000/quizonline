package com.quizonline.group8.mapper.resposemodel;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuizQuestionListResponseModel implements Serializable {
    private Integer memResult;
    private QuestionResponseModel question;
}
