package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.PayAliasTransactionDto;

public interface PayAliasService {

    CustomResponseEntity createAlias (Long customerId);

    CustomResponseEntity processTransaction(PayAliasTransactionDto transactionDto);
}
