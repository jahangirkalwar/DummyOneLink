package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.AccountDto;
import com.psp.dummyonelink.model.entity.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T11:31:41+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto jpeToDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setAccountType( account.getAccountType() );

        return accountDto;
    }

    @Override
    public Account dtoToJpe(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountType( accountDto.getAccountType() );

        return account;
    }
}
