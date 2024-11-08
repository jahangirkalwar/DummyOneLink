package com.psp.dummyonelink.controller;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.SwiftBankDto;
import com.psp.dummyonelink.service.SwiftBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/swiftbank")
public class SwiftBankController {

    @Autowired
    private SwiftBankService swiftBankService;

    @PostMapping
    public CustomResponseEntity createSwiftBank(@RequestBody SwiftBankDto dto){
        return swiftBankService.addSwiftBank(dto);
    }

    @GetMapping
    public CustomResponseEntity getBank (@RequestParam("swiftCode") String code){
        return swiftBankService.getBankBySwiftCode(code);
    }
}
