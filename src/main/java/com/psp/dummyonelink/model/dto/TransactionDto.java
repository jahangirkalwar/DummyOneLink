package com.psp.dummyonelink.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDto {

    @NotNull
    private String bankCode;
    @NotNull
    private String fromAccountNumber;
    @NotNull
    private String toAccountNumber;
    @NotNull
    private Double amount;
    private Date transactionDate;
    @NotNull
    private String purpose;
}
