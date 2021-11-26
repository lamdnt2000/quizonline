package com.quizonline.group8.dto;

import com.quizonline.group8.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponeQuizCategoryDTO {
    private Long exam_id;
    private Timestamp timeCreate;
    private Long examTime;
    private Integer numQuest;
    private String subjectID;
    private String examName;
    private Subject subject_id;
}
