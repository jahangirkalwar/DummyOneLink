package com.psp.dummyonelink.controller;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.BeneficiaryDto;
import com.psp.dummyonelink.model.dto.RemittanceCustomerDto;
import com.psp.dummyonelink.service.RemittanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/remittance")
public class RemittanceController {

    @Autowired
    private RemittanceService remittanceService;

    @PostMapping("/addCustomer")
    public CustomResponseEntity addCustomer(@RequestBody RemittanceCustomerDto dto){
        return remittanceService.createCustomer(dto);
    }

    @PostMapping("/beneficiary")
    public CustomResponseEntity confirmBeneficiary (@RequestBody BeneficiaryDto beneficiaryDto){
        return remittanceService.confirmBeneficiaryDetails(beneficiaryDto);
    }

}
