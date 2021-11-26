package com.quizonline.group8.mapper.impl;

import com.quizonline.group8.mapper.ResponseModelGenericMapper;
import com.quizonline.group8.mapper.dto.ChoiceDTO;
import com.quizonline.group8.mapper.resposemodel.ChoiceResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChoiceResponseModelMapper extends ResponseModelGenericMapper<ChoiceDTO, ChoiceResponseModel> {
}
