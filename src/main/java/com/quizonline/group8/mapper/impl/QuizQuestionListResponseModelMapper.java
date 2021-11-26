package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.ResponseModelGenericMapper;
import com.quizonline.group8.mapper.dto.QuizQuestionListDTO;
import com.quizonline.group8.mapper.resposemodel.QuizQuestionListResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizQuestionListResponseModelMapper extends ResponseModelGenericMapper<QuizQuestionListDTO, QuizQuestionListResponseModel> {

}
