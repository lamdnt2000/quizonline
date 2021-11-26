package com.quizonline.group8.dto;

import com.quizonline.group8.enumcode.ErrorCode;
import com.quizonline.group8.enumcode.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReponseDTO {
    private Object data;
    private SuccessCode successCode;
    private ErrorCode errorCode;
}
