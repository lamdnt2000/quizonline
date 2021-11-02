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
public class Choise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choice_ID;
    private Integer answer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id")
    private Question question;
}
