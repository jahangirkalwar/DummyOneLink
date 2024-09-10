package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.PayAliasDto;
import com.psp.dummyonelink.model.entity.PayAlias;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayAliasMapper {

    PayAliasDto jpeToDto(PayAlias payAlias);
    PayAlias dtoToJpe(PayAliasDto payAliasDto);
}
