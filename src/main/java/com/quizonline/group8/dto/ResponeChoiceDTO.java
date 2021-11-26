package com.quizonline.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponeChoiceDTO {
    private Long choice_ID;
    private Integer answerNumber;
    private String answer;
//    private Long quest_Id;
}
