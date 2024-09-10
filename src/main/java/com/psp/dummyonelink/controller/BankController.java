package com.psp.dummyonelink.controller;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.BankDto;
import com.psp.dummyonelink.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {


    @Autowired
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/addBank")
    public CustomResponseEntity addBank(@RequestBody BankDto bankDto){
        return bankService.addBank(bankDto);
    }

    @GetMapping("/getBank")
    public CustomResponseEntity getBankByReferenceNumber(@RequestParam String bankCode){
        return bankService.getBank(bankCode);
    }

    @GetMapping("/getAllBanks")
    public CustomResponseEntity getAllBanks(){
        return bankService.getAllBanks();
    }


}
