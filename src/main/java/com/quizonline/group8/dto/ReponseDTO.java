package com.quizonline.group8.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReponseDTO {
    private String successCode;
    private Object data;
    private String errorCode;
}
