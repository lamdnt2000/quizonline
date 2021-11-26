package com.quizonline.group8.model;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_id;
    private Timestamp timeCreate;
    private Long examTime;
    private Integer numQuest;
    private String examName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "quizcategory")
    private Collection<Quiz> quizzes;

}
