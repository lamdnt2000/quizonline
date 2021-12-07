package com.quizonline.group8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long quest_ID;
    private String questionTitle;
    private Integer correctAnswer;
    private Integer status;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
    @JsonIgnore
    private String userCreate;
    @JsonIgnore
    private String userUpdate;

    @OneToMany(mappedBy = "question")
    private Collection<Choise> choice;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private Collection<QuizQuestionList> quizQuestionLists;
}
