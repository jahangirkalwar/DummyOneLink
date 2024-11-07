package com.psp.dummyonelink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDto {


    private String cnicNo;
    private Long id;
    private String accountNumber;
    private String accountTitle;
    private String accountStatus;
    private String accountType;
    private String accountDescription;
    private String email;
    private Date accountOpenDate;
    private Double accountBalance;
    private Date accountClosedDate;
    private String accountClosedReason;
    private String proofOfIncome;
    private String branchCode;
    private String bankName;
    private String icon;



}
