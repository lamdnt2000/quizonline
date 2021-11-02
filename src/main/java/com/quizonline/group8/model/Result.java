package com.quizonline.group8.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long result_Id;
    private Double total;
    private Timestamp timeFinish;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
