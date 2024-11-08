package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.SwiftBankDto;

public interface SwiftBankService {

    CustomResponseEntity addSwiftBank (SwiftBankDto bankDto);

    CustomResponseEntity getBankBySwiftCode(String code);
}
