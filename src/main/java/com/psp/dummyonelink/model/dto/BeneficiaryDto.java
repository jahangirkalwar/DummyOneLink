package com.psp.dummyonelink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryDto {

    private String accountNumber;
    private String bankSwiftCode;
    private String bankName;
    private String beneficiaryName;
    private String address;



}
