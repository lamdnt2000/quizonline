package com.quizonline.group8.model;

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
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quiz_Id;
    private Integer resultId;
    private Long member_Id;
    private Timestamp dateCreate;
    private Timestamp dateSubmit;
    private Integer exam_Id;
    private Integer status;
    private Float total;
    private Long quizTime;

    @OneToMany(mappedBy = "quiz")
    private List<Result> resultList;

    @ManyToMany(mappedBy = "quiz")
    private Set<Member> members;

    @ManyToOne(fetch =  FetchType.EAGER)
    private QuizCategory quizcategory;

    @OneToMany(mappedBy = "quiz")
    private Collection<QuizQuestionList> quizQuestionLists;

}
