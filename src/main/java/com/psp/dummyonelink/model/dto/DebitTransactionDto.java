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
public class DebitTransactionDto {
    private String bankCode;
    private String fromAccountNumberOrIbanCode;
    private String toAccountNumberOrIbanCode;
    private Double amount;
    private Date transactionDate;
    private String purpose;
}
