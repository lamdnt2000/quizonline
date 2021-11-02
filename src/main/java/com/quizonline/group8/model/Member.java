package com.quizonline.group8.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Member implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;
    private String password;
    private String fullname;
    private Long role_id;
    private String status;

    @ManyToMany
    private Set<Quiz> quiz;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "role_Id")
    private Role roles;

}
