package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.ResponseModelGenericMapper;
import com.quizonline.group8.mapper.dto.SubjectDTO;
import com.quizonline.group8.mapper.resposemodel.SubjectResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubjectResponseModelMapper extends ResponseModelGenericMapper<SubjectDTO, SubjectResponseModel> {
}
