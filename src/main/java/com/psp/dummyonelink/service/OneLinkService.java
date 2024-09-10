package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.AccountDetailsDto;
import com.psp.dummyonelink.model.dto.DebitTransactionDto;
import com.psp.dummyonelink.model.dto.TransactionDto;
import com.psp.dummyonelink.model.entity.Bank;

public interface OneLinkService {
    
    

    CustomResponseEntity getPaymentServiceProvider(String name);

    CustomResponseEntity fetchAccountTitle (String accountNumber, String bankCode);

    CustomResponseEntity processTransaction(TransactionDto transactionDto);

    AccountDetailsDto fetchAccountDetails(String accountNumber, String bankName);

}
