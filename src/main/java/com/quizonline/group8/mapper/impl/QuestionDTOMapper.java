package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.DTOGenericMapper;
import com.quizonline.group8.mapper.dto.QuestionDTO;
import com.quizonline.group8.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionDTOMapper extends DTOGenericMapper<Question, QuestionDTO> {

}
