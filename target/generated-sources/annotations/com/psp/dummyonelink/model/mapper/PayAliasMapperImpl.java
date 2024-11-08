package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.PayAliasDto;
import com.psp.dummyonelink.model.entity.PayAlias;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-08T14:27:06+0500",
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
        if ( payAlias.getCreationDate() != null ) {
            payAliasDto.setCreationDate( DateTimeFormatter.ISO_LOCAL_DATE.format( payAlias.getCreationDate() ) );
        }
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
        if ( payAliasDto.getCreationDate() != null ) {
            payAlias.setCreationDate( LocalDate.parse( payAliasDto.getCreationDate() ) );
        }
        payAlias.setStatus( payAliasDto.getStatus() );

        return payAlias;
    }
}
