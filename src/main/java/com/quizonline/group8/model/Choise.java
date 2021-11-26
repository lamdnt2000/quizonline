package com.quizonline.group8.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Choise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choice_ID;
    private Integer answerNumber;
    private String answer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id")
    private Question question;
}
