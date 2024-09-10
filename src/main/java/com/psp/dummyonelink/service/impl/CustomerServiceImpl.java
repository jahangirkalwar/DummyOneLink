package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.CustomerDto;
import com.psp.dummyonelink.model.entity.Account;
import com.psp.dummyonelink.model.entity.Bank;
import com.psp.dummyonelink.model.entity.Customer;
import com.psp.dummyonelink.model.entity.PayAlias;
import com.psp.dummyonelink.model.mapper.CustomerMapper;
import com.psp.dummyonelink.repo.AccountRepository;
import com.psp.dummyonelink.repo.BankRepo;
import com.psp.dummyonelink.repo.CustomerRepo;
import com.psp.dummyonelink.repo.PayAliasRepository;
import com.psp.dummyonelink.service.CustomerService;
import com.psp.dummyonelink.util.AccountUtil;
import com.psp.dummyonelink.util.PayAliasUtil;
import jakarta.transaction.Transactional;
import org.hibernate.collection.spi.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PayAliasRepository payAliasRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomResponseEntity addCustomer(CustomerDto customerDto) {

        Optional<Bank> exists = Optional.ofNullable(bankRepo.findByBankCode(customerDto.getBankCode()));
        if (!exists.isPresent()) {
            return new CustomResponseEntity(404, "bank not found!");
        }

        Bank bank = exists.get();
        String accountNumber = AccountUtil.generateAccountNumber("PK00");
        String ibanCode = AccountUtil.generateIbanNumber("PK00");

        boolean emailExists = bank.getCustomers().stream()
                .anyMatch(customer -> customer.getEmail().equals(customerDto.getEmail()));

        if (emailExists) {
            return new CustomResponseEntity<>("Customer with this email already exists in this bank");
        }

        boolean cnicExists = bank.getCustomers().stream()
                .anyMatch(customer -> customer.getCnic().equals(customerDto.getCnic()));

        if (cnicExists) {
            return new CustomResponseEntity<>("Customer with this CNIC already exists in this bank");
        }

        boolean mobileNumberExists = bank.getCustomers().stream()
                .anyMatch(customer -> customer.getMobileNumber().equals(customerDto.getMobileNumber()));

        if (mobileNumberExists) {
            return new CustomResponseEntity<>("Customer with this mobile number already exists in this bank");
        }

        Customer customer1 = customerMapper.dtoToJpe(customerDto);
        customer1.setRegisteredDate(String.valueOf(LocalDateTime.now()));
        customer1.setBank(bank);
        bank.getCustomers().add(customer1);
        bankRepo.save(bank);

        Account account = new Account();
        account.setBalance(0.00);
        account.setAccountType("current");
        account.setBank(bank);
        account.setAccountTitle(customer1.getFirstName() + " " + customer1.getLastName());
        account.setCreatedAt(new Date());
        account.setAccountStatus("ACTIVE");
        account.setProofOfIncome("Employed");
        account.setCustomer(customer1);
        accountRepository.save(account);



        account.setAccountNumber(accountNumber);
        account.setIbanCode(ibanCode);


        List<PayAlias> allAliases = payAliasRepository.findAll();
        Set<String> existingPayIds = new HashSet<>();

        for (PayAlias alias : allAliases) {
            existingPayIds.add(alias.getOnePayId());
        }

        String unique1PayId = generateAndValidateUnique1PayId(existingPayIds);

        PayAlias alias = new PayAlias();
        alias.setOnePayId(unique1PayId);
        alias.setCustomer(customer1);
        customer1.setPayAlias(alias);
        alias.setStatus("ACTIVE");
        alias.setCreationDate(LocalDate.now());

        payAliasRepository.save(alias);

        return new CustomResponseEntity("customer added successfully");
    }

    @Transactional
    public Customer save(CustomerDto customerDto) {

        Customer customer = customerMapper.dtoToJpe(customerDto);
        return customerRepo.save(customer);
    }

    public static String generateAndValidateUnique1PayId(Set<String> existingIds) {
        String unique1PayId;
        do {
            unique1PayId = PayAliasUtil.generateUnique1PayId();
        } while (existingIds.contains(unique1PayId));

        return unique1PayId;
    }


}
