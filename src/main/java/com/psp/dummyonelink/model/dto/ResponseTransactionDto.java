package com.psp.dummyonelink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseTransactionDto {
    private String transactionId;
    private Double amount;
    private LocalDateTime transactionDate;
    private String purpose;
}
