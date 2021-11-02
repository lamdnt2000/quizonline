package com.quizonline.group8.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long result_Id;
    private Double total;
    private Timestamp timeFinish;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id",unique=true)
    private Quiz quiz;
}
