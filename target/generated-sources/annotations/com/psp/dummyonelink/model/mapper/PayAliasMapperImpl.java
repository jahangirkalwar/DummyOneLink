package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.PayAliasDto;
import com.psp.dummyonelink.model.entity.PayAlias;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T11:40:49+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class PayAliasMapperImpl implements PayAliasMapper {

    @Override
    public PayAliasDto jpeToDto(PayAlias payAlias) {
        if ( payAlias == null ) {
            return null;
        }

        PayAliasDto payAliasDto = new PayAliasDto();

        payAliasDto.setOnePayId( payAlias.getOnePayId() );
        payAliasDto.setCreationDate( payAlias.getCreationDate() );
        payAliasDto.setStatus( payAlias.getStatus() );

        return payAliasDto;
    }

    @Override
    public PayAlias dtoToJpe(PayAliasDto payAliasDto) {
        if ( payAliasDto == null ) {
            return null;
        }

        PayAlias payAlias = new PayAlias();

        payAlias.setOnePayId( payAliasDto.getOnePayId() );
        payAlias.setCreationDate( payAliasDto.getCreationDate() );
        payAlias.setStatus( payAliasDto.getStatus() );

        return payAlias;
    }
}
