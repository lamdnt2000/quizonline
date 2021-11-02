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
@Getter
@Setter
public class QuizCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_id;
    private Timestamp timeCreate;
    private Long examTime;
    private Integer numQuest;
    private String subjectID;
    private String examName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @OneToMany(mappedBy = "quizcategory")
    private Collection<Quiz> quizzes;

}
