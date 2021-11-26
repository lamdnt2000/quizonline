package com.quizonline.group8.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long role_Id;
    private String roleName;

    @OneToMany(mappedBy = "roles")
    private Collection<Member> members;

}
