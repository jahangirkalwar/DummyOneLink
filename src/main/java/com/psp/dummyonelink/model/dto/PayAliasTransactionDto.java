package com.psp.dummyonelink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PayAliasTransactionDto {
    private String bankCode;
    private String onePayId;
    private String accountTitle;
    private Double amount;
    private Date transactionDate;
    private String purpose;
}
