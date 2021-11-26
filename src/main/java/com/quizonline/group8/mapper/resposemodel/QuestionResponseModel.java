package com.quizonline.group8.mapper.resposemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuestionResponseModel implements Serializable {

    private Long quest_ID;
    private String questionTitle;
    private List<ChoiceResponseModel> choice;
}
