package com.quizonline.group8.mapper.resposemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SubjectResponseModel implements Serializable {
    private Long subject_Id;
    private String subjectName;
}
