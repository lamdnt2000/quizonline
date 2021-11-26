package com.quizonline.group8.mapper.resposemodel;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ChoiceResponseModel implements Serializable {

    private Integer answerNumber;
    private String answer;
}
