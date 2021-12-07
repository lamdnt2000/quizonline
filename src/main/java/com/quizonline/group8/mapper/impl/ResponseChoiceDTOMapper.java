package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.DTOGenericMapper;
import com.quizonline.group8.mapper.dto.ResponeChoiceDTO;
import com.quizonline.group8.model.Choise;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ResponseChoiceDTOMapper extends DTOGenericMapper<Choise, ResponeChoiceDTO> {
}
