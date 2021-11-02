package com.quizonline.group8.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuizQuestionList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qql_Id;

    private Integer memResult;

    @ManyToOne(fetch =  FetchType.EAGER)
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
