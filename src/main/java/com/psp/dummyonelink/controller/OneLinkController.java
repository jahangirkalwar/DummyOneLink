package com.psp.dummyonelink.controller;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.AccountDetailsDto;
import com.psp.dummyonelink.model.dto.DebitTransactionDto;
import com.psp.dummyonelink.model.dto.PayAliasDto;
import com.psp.dummyonelink.model.dto.TransactionDto;
import com.psp.dummyonelink.service.AccountService;
import com.psp.dummyonelink.service.OneLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/1link")
public class OneLinkController {

    @Autowired
    private final OneLinkService oneLinkService;

    @Autowired
    private AccountService accountService;

    public OneLinkController(OneLinkService oneLinkService) {
        this.oneLinkService = oneLinkService;
    }

    @GetMapping("/getPSP")
    CustomResponseEntity getPaymentServiceProvider(@RequestParam String name){
        return oneLinkService.getPaymentServiceProvider(name);
    }

    @GetMapping("/fetchAccountTitle")
    public CustomResponseEntity fetchAccountByTitleAndBank (@RequestParam String accountNumber, @RequestParam String bankCode){
        return oneLinkService.fetchAccountTitle(accountNumber,bankCode);
    }
    @PostMapping("/creditTransaction") //
    public CustomResponseEntity creditTransaction (@RequestBody TransactionDto transactionDto){
        return oneLinkService.processTransaction(transactionDto);
    }

    @GetMapping("/getAccount")
    public AccountDetailsDto fetchAccountDetailsForBeneficiary(@RequestParam String accountNumber, @RequestParam String bankName){
        return oneLinkService.fetchAccountDetails(accountNumber,bankName);
    }

}
