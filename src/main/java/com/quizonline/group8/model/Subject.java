package com.quizonline.group8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private Collection<QuizCategory> quizCategories;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private Collection<Question> questions;
}
