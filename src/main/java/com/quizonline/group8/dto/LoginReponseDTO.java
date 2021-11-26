package com.quizonline.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReponseDTO {
    private String email;
    private String password;
    private String fullname;
    private String role;
    private String status;
    private String token;
}
