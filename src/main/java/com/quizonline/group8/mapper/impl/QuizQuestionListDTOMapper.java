package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.DTOGenericMapper;
import com.quizonline.group8.mapper.dto.QuizQuestionListDTO;
import com.quizonline.group8.model.QuizQuestionList;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizQuestionListDTOMapper extends DTOGenericMapper<QuizQuestionList, QuizQuestionListDTO> {
}
