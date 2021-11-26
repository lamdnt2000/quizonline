package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.ResponseModelGenericMapper;
import com.quizonline.group8.mapper.dto.QuizDTO;
import com.quizonline.group8.mapper.resposemodel.QuizResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizResponseModelMapper extends ResponseModelGenericMapper<QuizDTO, QuizResponseModel> {
}
