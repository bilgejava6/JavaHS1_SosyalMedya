package com.muhammet.mapper;

import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfile fromRequestDto(UserProfileCreateRequestDto dto);
}
