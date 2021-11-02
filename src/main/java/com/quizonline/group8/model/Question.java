package com.quizonline.group8.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long quest_ID;
    private String questionTitle;
    private String subjectID;
    private Integer correctAnswer;
    private Integer status;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
    private String userCreate;
    private String userUpdate;

    @OneToMany(mappedBy = "question")
    private List<Choise> choice;

    @ManyToOne(fetch =  FetchType.EAGER)
    private Subject subject;

    @OneToMany(mappedBy = "question")
    private Collection<QuizQuestionList> quizQuestionLists1;
}
