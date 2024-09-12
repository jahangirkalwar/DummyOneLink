package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.AccountDto;
import com.psp.dummyonelink.model.entity.Account;
import com.psp.dummyonelink.model.entity.Bank;
import com.psp.dummyonelink.model.entity.Customer;
import com.psp.dummyonelink.model.mapper.AccountMapper;
import com.psp.dummyonelink.repo.AccountRepository;
import com.psp.dummyonelink.repo.BankRepo;
import com.psp.dummyonelink.repo.CustomerRepo;
import com.psp.dummyonelink.repo.TransactionRepository;
import com.psp.dummyonelink.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepo customerRepo;


    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public CustomResponseEntity createAccount(AccountDto accountDto) {

        Optional<Bank> bank = Optional.ofNullable(bankRepo.findByBankCode(accountDto.getBankCode()));
        if (!bank.isPresent()) {
            return new CustomResponseEntity(404, "bank not found!");
        }
        Optional<Customer> customer = customerRepo.findByEmailAndBank(accountDto.getEmail(),accountDto.getBankCode());
        if (!customer.isPresent()) {
            return new CustomResponseEntity("customer not exist");
        }

        logger.info("creating account ............");
        String accountNumber = generateAccountNumber("PK00");
        String ibanCode = generateIbanNumber("PK00",accountDto.getBankCode());

        Account newAccount = accountMapper.dtoToJpe(accountDto);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setIbanCode(ibanCode);
        newAccount.setCustomer(customer.get());
        newAccount.setAccountTitle(customer.get().getFirstName() +" "+customer.get().getLastName());
        newAccount.setAccountType(accountDto.getAccountType());
        newAccount.setBalance(0.00);
        newAccount.setCreatedAt(new Date());
        newAccount.setAccountStatus("ACTIVE");
        newAccount.setProofOfIncome("Employed");
        newAccount.setBank(bank.get());
        Account savedAccount = accountRepository.save(newAccount);
        return new CustomResponseEntity(savedAccount.getAccountNumber(), "Account Created Successfully");
    }

    @Override
    public CustomResponseEntity findAccountTitleByAccountNumberAndBank(String accountNumber, String bankCode) {
        String accountTitle = accountRepository.findAccountTitleByAccountNumberAndBank_BankCode(accountNumber, bankCode);
        if (accountTitle.isEmpty()) {
            return new CustomResponseEntity("Account does not exist");
        }
        return new CustomResponseEntity(HttpStatus.OK, accountTitle);
    }

    private static String generateIbanNumber(String prefix,String bankCode) {
        String uniqueIdentifier = UUID.randomUUID().toString().replaceAll("-", "");
        return prefix + bankCode + 00 + uniqueIdentifier.substring(0, Math.min(10, uniqueIdentifier.length()));
    }

    private static String generateAccountNumber(String prefix) {
        String uniqueIdentifier = UUID.randomUUID().toString().replaceAll("-", "");
        return prefix + uniqueIdentifier.substring(0, Math.min(7, uniqueIdentifier.length()));
    }

}
