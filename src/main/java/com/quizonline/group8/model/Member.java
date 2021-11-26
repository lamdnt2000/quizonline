package com.quizonline.group8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member implements Serializable {
    @Id
    private String email;
    private String password;
    private String fullname;
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private Collection<Quiz> quizzes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role roles;
}
