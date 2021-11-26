package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.DTOGenericMapper;
import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubjectDTOMapper extends DTOGenericMapper<Subject, SubjectDTO> {

}
