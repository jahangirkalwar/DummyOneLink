package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.AccountDto;
import com.psp.dummyonelink.model.dto.TransactionDto;

public interface AccountService {


    CustomResponseEntity createAccount(AccountDto accountDto);


    CustomResponseEntity findAccountTitleByAccountNumberAndBank(String accountNumber,String bankCode);

    CustomResponseEntity getDonationAccounts();
}
