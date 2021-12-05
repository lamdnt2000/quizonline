package com.quizonline.group8.dto;

import com.quizonline.group8.model.Subject;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ResponseQuizCategoryDTO {
    private Long exam_id;
    private Timestamp timeCreate;
    @NotNull
    private Long examTime;
    @NotNull
    private Integer numQuest;
    private Subject subjectID;
    @NotBlank(message = "Exam name required")
    private String examName;
}
