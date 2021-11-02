package com.quizonline.group8.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuizQuestionList implements Serializable {

    private Integer memResult;

    @Id
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "quest_id")
    private Question question;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
