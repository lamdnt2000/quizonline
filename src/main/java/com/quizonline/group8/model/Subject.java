package com.quizonline.group8.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;


@Entity
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_Id;
    private String subjectName;
    private Timestamp dateCreate;

    @OneToMany(mappedBy = "subject")
    private Collection<QuizCategory> quizCategories;

    @OneToMany(mappedBy = "subject")
    private Collection<Question> questions;
}
