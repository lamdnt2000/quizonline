package com.quizonline.group8.mapper.resposemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberResponseModel implements Serializable {
    private String email;
    private String password;
    private String fullname;
    private String status;
    private RoleResponseModel roles;
}
