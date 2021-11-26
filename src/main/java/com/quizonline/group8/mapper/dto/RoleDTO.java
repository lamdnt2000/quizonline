package com.quizonline.group8.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RoleDTO implements Serializable {
    private Long role_Id;
    private String roleName;
}
