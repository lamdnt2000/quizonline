package com.quizonline.group8.dto;

import com.quizonline.group8.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionQuerySearchDTO {
    private String title="";
    private Subject subject;
    private Integer page=1;
    private Integer status=1;
}
