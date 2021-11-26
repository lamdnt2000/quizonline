package com.quizonline.group8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;


@Entity
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
