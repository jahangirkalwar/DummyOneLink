package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.BeneficiaryDto;
import com.psp.dummyonelink.model.dto.RemittanceCustomerDto;

public interface RemittanceService {
    CustomResponseEntity createCustomer(RemittanceCustomerDto dto);

    CustomResponseEntity confirmBeneficiaryDetails(BeneficiaryDto beneficiaryDto);
}
