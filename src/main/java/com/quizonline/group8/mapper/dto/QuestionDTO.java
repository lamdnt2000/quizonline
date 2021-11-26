package com.quizonline.group8.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuestionDTO implements Serializable {

    private Long quest_ID;
    private String questionTitle;
    private List<ChoiceDTO> choice;

}
