package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.AccountDto;
import com.psp.dummyonelink.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto jpeToDto(Account account);
    Account dtoToJpe(AccountDto accountDto);
}
