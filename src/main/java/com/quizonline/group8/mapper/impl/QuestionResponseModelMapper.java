package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.ResponseModelGenericMapper;
import com.quizonline.group8.mapper.dto.QuestionDTO;
import com.quizonline.group8.mapper.resposemodel.QuestionResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionResponseModelMapper extends ResponseModelGenericMapper<QuestionDTO, QuestionResponseModel> {
}
