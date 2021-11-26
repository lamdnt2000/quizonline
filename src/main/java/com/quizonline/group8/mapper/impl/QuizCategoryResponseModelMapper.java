package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.ResponseModelGenericMapper;
import com.quizonline.group8.mapper.dto.QuizCategoryDTO;
import com.quizonline.group8.mapper.resposemodel.QuizCategoryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizCategoryResponseModelMapper extends ResponseModelGenericMapper<QuizCategoryDTO, QuizCategoryResponseModel> {
}
