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
@Getter
@Setter
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quiz_Id;
    private Timestamp dateCreate;
    private Timestamp dateSubmit;
    private Integer status;
    private Float total;
    private Long quizTime;


    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "email")
    private Member member;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "exam_Id")
    private QuizCategory quizcategory;

    @OneToMany(mappedBy = "quiz")
    private Collection<QuizQuestionList> quizQuestionLists;

}
