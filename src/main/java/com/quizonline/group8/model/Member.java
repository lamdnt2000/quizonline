package com.quizonline.group8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Member implements Serializable {

    @Id
    private String email;
    private String password;
    private String fullname;
    private String status;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private Collection<Quiz> quizzes;
    @JsonIgnore
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "role_Id")
    private Role roles;

}
