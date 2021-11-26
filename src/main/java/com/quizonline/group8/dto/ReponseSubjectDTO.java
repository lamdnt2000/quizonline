package com.quizonline.group8.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReponseSubjectDTO {
    private Long subject_Id;
    private Timestamp dateCreate;
    private String subjectName;
}
