package com.quizonline.group8.mapper.resposemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RoleResponseModel implements Serializable {
    private Long role_Id;
    private String roleName;
}
