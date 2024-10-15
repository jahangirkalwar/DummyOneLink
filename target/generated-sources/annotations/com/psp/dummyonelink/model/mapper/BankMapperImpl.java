package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.BankDto;
import com.psp.dummyonelink.model.entity.Bank;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T14:24:37+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class BankMapperImpl implements BankMapper {

    @Override
    public BankDto jpeToDto(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        BankDto bankDto = new BankDto();

        bankDto.setSecretKey( bank.getSecretKey() );
        bankDto.setBankName( bank.getBankName() );
        bankDto.setBankDesc( bank.getBankDesc() );
        bankDto.setBankCode( bank.getBankCode() );
        bankDto.setCity( bank.getCity() );
        bankDto.setState( bank.getState() );
        bankDto.setCountry( bank.getCountry() );

        return bankDto;
    }

    @Override
    public Bank dtoToJpe(BankDto bankDto) {
        if ( bankDto == null ) {
            return null;
        }

        Bank bank = new Bank();

        bank.setBankCode( bankDto.getBankCode() );
        bank.setBankName( bankDto.getBankName() );
        bank.setBankDesc( bankDto.getBankDesc() );
        bank.setCountry( bankDto.getCountry() );
        bank.setState( bankDto.getState() );
        bank.setCity( bankDto.getCity() );
        bank.setSecretKey( bankDto.getSecretKey() );

        return bank;
    }
}
