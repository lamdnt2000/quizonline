package com.quizonline.group8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class QuizQuestionListID implements Serializable {
    private Long quest_id;
    private Long quiz_id;
}
