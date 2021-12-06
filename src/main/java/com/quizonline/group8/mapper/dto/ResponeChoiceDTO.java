package com.quizonline.group8.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponeChoiceDTO implements Serializable {
    private Long choice_ID;
    private Integer answernumber;
    private String answer;
}
