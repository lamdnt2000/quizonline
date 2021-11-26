package com.quizonline.group8.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class QuizQuestionList implements Serializable {

    private Integer memResult;
    @EmbeddedId
    protected QuizQuestionListID quizQuestionListID;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "quest_id",insertable = false, updatable = false)
    private Question question;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id",insertable = false, updatable = false)
    private Quiz quiz;
}
