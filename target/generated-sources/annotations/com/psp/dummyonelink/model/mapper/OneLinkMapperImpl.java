package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.OneLinkDto;
import com.psp.dummyonelink.model.entity.OneLink;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T14:24:37+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class OneLinkMapperImpl implements OneLinkMapper {

    @Override
    public OneLinkDto jpeToDto(OneLink oneLink) {
        if ( oneLink == null ) {
            return null;
        }

        OneLinkDto oneLinkDto = new OneLinkDto();

        oneLinkDto.setId( oneLink.getId() );
        oneLinkDto.setName( oneLink.getName() );
        oneLinkDto.setDescription( oneLink.getDescription() );
        oneLinkDto.setCreationDate( oneLink.getCreationDate() );

        return oneLinkDto;
    }

    @Override
    public OneLink dtoToJpe(OneLinkDto oneLinkDto) {
        if ( oneLinkDto == null ) {
            return null;
        }

        OneLink oneLink = new OneLink();

        oneLink.setId( oneLinkDto.getId() );
        oneLink.setName( oneLinkDto.getName() );
        oneLink.setDescription( oneLinkDto.getDescription() );
        oneLink.setCreationDate( oneLinkDto.getCreationDate() );

        return oneLink;
    }
}
