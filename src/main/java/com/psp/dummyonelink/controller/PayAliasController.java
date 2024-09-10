package com.psp.dummyonelink.controller;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.PayAliasTransactionDto;
import com.psp.dummyonelink.service.PayAliasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alias")
public class PayAliasController {

    @Autowired
    private PayAliasService payAliasService;


    @PostMapping("/create")
    public CustomResponseEntity createAlias (@RequestParam("customerId") Long customerId){
        return payAliasService.createAlias(customerId);
    }

    @PostMapping("/creditTransaction")
    public CustomResponseEntity processTransaction (@RequestBody PayAliasTransactionDto transactionDto){
        return payAliasService.processTransaction(transactionDto);
    }




}
