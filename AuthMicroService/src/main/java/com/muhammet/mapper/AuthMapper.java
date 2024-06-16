package com.muhammet.mapper;

import com.muhammet.dto.request.AuthRegisterReqestDto;
import com.muhammet.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromAuthRegisterRequestDto(AuthRegisterReqestDto dto);
}
