package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.BankDto;

public interface BankService {

    CustomResponseEntity addBank(BankDto bankDto);

    CustomResponseEntity getBank (String bankCode);

    CustomResponseEntity getAllBanks();
    boolean verifyBank(String bankCode);

}
