package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.DTOGenericMapper;
import com.quizonline.group8.mapper.dto.ChoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.awt.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChoiceDTOMapper extends DTOGenericMapper<Choice, ChoiceDTO> {

}
