package com.quizonline.group8.dto;

import com.quizonline.group8.model.Subject;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ResponseQuizCategoryDTO {
    private Long exam_id;
    private Timestamp timeCreate;
    private Long examTime;
    private Integer numQuest;
    private Subject subjectID;
    private String examName;
}
