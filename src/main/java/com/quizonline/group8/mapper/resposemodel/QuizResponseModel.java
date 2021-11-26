package com.quizonline.group8.mapper.resposemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizResponseModel implements Serializable {
    private Long quiz_Id;
    private Timestamp dateCreate;
    private Timestamp dateSubmit;
    private Integer status;
    private Float total;
    private Long quizTime;
    private QuizCategoryResponseModel quizcategory;
}
