package com.quizonline.group8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quiz_Id;
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
    @JsonIgnore
    @OneToMany(mappedBy = "quiz")
    private Collection<QuizQuestionList> quizQuestionLists;

}
