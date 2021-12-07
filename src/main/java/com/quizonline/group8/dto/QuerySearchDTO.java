package com.quizonline.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuerySearchDTO implements Serializable {
    private String title="";
    private Integer page=1;
}
