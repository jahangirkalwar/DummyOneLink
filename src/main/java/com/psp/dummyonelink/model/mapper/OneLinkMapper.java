package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.OneLinkDto;
import com.psp.dummyonelink.model.entity.OneLink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OneLinkMapper {


    OneLinkDto jpeToDto(OneLink oneLink);
    OneLink dtoToJpe(OneLinkDto oneLinkDto);
}
