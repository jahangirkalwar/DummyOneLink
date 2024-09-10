package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.BankDto;
import com.psp.dummyonelink.model.entity.Bank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankDto jpeToDto(Bank bank);
    Bank dtoToJpe(BankDto bankDto);
}
