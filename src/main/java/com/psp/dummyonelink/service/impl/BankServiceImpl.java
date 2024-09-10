package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.exception.ResourceFoundException;
import com.psp.dummyonelink.exception.ResourceNotFoundException;
import com.psp.dummyonelink.model.dto.BankDto;
import com.psp.dummyonelink.model.entity.Bank;
import com.psp.dummyonelink.model.entity.OneLink;
import com.psp.dummyonelink.repo.BankRepo;
import com.psp.dummyonelink.repo.OneLinkRepo;
import com.psp.dummyonelink.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private final BankRepo bankRepo;

    @Autowired
    private final OneLinkRepo oneLinkRepo;

    public BankServiceImpl(BankRepo bankRepo, OneLinkRepo oneLinkRepo) {
        this.bankRepo = bankRepo;
        this.oneLinkRepo = oneLinkRepo;
    }

    @Override
    public CustomResponseEntity addBank(BankDto bankDto) {

        BankDto savedBankDto = null;

        Optional<Bank> bank = bankRepo.findById(bankDto.getBankCode());

        if (bank.isPresent()) {
            throw new ResourceFoundException("Bank Already Exist!");
        }

        Optional<OneLink> oneLink = oneLinkRepo.findById(bankDto.getOneLinkId());

        if (!oneLink.isPresent()) {
            return new CustomResponseEntity<>(404, "oneLink not found");
        }


        Bank bankJpe = dtoToJpe(bankDto);
        Bank savedBank = bankRepo.save(bankJpe);
        savedBankDto = jpeToDto(savedBank);

        return new CustomResponseEntity(savedBankDto, "Banked Added Successfully!");
    }

    @Override
    public CustomResponseEntity getBank(String bankCode) {

        Bank bank = Optional.ofNullable(bankRepo.findById(bankCode).orElseThrow(() -> new ResourceNotFoundException("Bank doesn't exist"))).get();

        BankDto bankDto = jpeToDto(bank);
        return new CustomResponseEntity(bankDto, "bank found!");
    }

    @Override
    public CustomResponseEntity getAllBanks() {
        List<Bank> bankList = bankRepo.findAll();
        List<BankDto> bankDtoList = bankList.stream().map(bank -> jpeToDto(bank))
                .collect(Collectors.toList());

        return new CustomResponseEntity(bankDtoList, "list of banks!");
    }

    @Override
    public boolean verifyBank(String bankCode) {

        Optional<Bank> byId = Optional.ofNullable(bankRepo.findByBankCode(bankCode));
        if (byId == null) {
            throw new ResourceNotFoundException("bank not exist");
        }

        return byId.isPresent();
    }


    private static Bank dtoToJpe(BankDto bankDto) {
        Bank bank = new Bank();
        bank.setBankName(bankDto.getBankName());
        bank.setBankCode(bankDto.getBankCode());
        bank.setBankDesc(bankDto.getBankDesc());
        bank.setCity(bankDto.getCity());
        bank.setCountry(bankDto.getCountry());
        bank.setState(bankDto.getState());
        bank.setSecretKey(bankDto.getSecretKey());
        OneLink oneLink = new OneLink();
        oneLink.setId(bankDto.getOneLinkId());
        bank.setOneLink(oneLink);
        return bank;
    }

    private static BankDto jpeToDto(Bank bank) {
        BankDto bankDto = new BankDto();
        bankDto.setBankName(bank.getBankName());
        bankDto.setBankCode(bank.getBankCode());
        bankDto.setBankDesc(bank.getBankDesc());
        bankDto.setCity(bank.getCity());
        bankDto.setCountry(bank.getCountry());
        bankDto.setState(bank.getState());
        bankDto.setSecretKey(bank.getSecretKey());
        bankDto.setOneLinkId(bank.getOneLink().getId());
        return bankDto;
    }


}
