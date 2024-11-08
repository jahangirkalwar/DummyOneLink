package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.SwiftBankDto;
import com.psp.dummyonelink.model.entity.Swift;
import com.psp.dummyonelink.model.entity.SwiftBank;
import com.psp.dummyonelink.repo.SwiftBankRepo;
import com.psp.dummyonelink.service.SwiftBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SwiftBankServiceImpl implements SwiftBankService {

    @Autowired
    private SwiftBankRepo swiftBankRepo;
    @Override
    public CustomResponseEntity addSwiftBank(SwiftBankDto bankDto) {

        if(bankDto==null){
            return CustomResponseEntity.error("data can not be null");
        }

        SwiftBank existingSwiftBank = swiftBankRepo.findByBankSwiftCode(bankDto.getBankSwiftCode());

        if(existingSwiftBank!=null){
            return CustomResponseEntity.error("swift bank already exists with this code!");
        }

        SwiftBank bank = mapToEntity(bankDto);

        SwiftBank saved = swiftBankRepo.save(bank);
        SwiftBankDto dto = maptoDto(saved);

        return new CustomResponseEntity(dto,"swift bank added!");
    }

    @Override
    public CustomResponseEntity getBankBySwiftCode(String code) {

        if(code.isEmpty() || code.isBlank()){
            return CustomResponseEntity.error("swift code can not be empty");
        }

        SwiftBank byBankSwiftCode = swiftBankRepo.findByBankSwiftCode(code);
        SwiftBankDto dto = maptoDto(byBankSwiftCode);

        return new CustomResponseEntity(dto.getName(), "Bank Name");
    }

    private SwiftBankDto maptoDto(SwiftBank bank) {
        SwiftBankDto dto = new SwiftBankDto();
        dto.setId(bank.getId());
        dto.setName(bank.getName());
        dto.setCreatedAt(bank.getCreatedAt());
        dto.setCity(bank.getCity());
        dto.setAddress(bank.getAddress());
        dto.setCountry(bank.getCountry());
        dto.setBankSwiftCode(bank.getBankSwiftCode());
        dto.setSwiftId(bank.getSwift().getId());
        return dto;
    }

    private SwiftBank mapToEntity(SwiftBankDto bankDto) {

        SwiftBank bank = new SwiftBank();
        bank.setName(bankDto.getName());
        bank.setBankSwiftCode(bankDto.getBankSwiftCode());
        bank.setCity(bankDto.getCity());
        bank.setCountry(bankDto.getCountry());
        bank.setAddress(bankDto.getAddress());
        bank.setCreatedAt(LocalDateTime.now());
        Swift swift = new Swift();
        swift.setId(bankDto.getSwiftId());
        bank.setSwift(swift);
        return bank;
    }
}
