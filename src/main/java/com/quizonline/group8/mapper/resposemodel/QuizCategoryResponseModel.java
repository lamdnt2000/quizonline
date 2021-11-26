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
public class QuizCategoryResponseModel implements Serializable {
    private Long exam_id;
    private Long examTime;
    private Integer numQuest;
    private String examName;
    private Timestamp timeCreate;
    private SubjectResponseModel subject;

}
