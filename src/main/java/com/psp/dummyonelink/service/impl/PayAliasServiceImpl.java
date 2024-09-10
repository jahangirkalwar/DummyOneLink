package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.exception.ResourceNotFoundException;
import com.psp.dummyonelink.model.dto.PayAliasTransactionDto;
import com.psp.dummyonelink.model.entity.*;
import com.psp.dummyonelink.model.generic.GenericDao;
import com.psp.dummyonelink.model.mapper.PayAliasMapper;
import com.psp.dummyonelink.repo.AccountRepository;
import com.psp.dummyonelink.repo.BankRepo;
import com.psp.dummyonelink.repo.PayAliasRepository;
import com.psp.dummyonelink.repo.TransactionRepository;
import com.psp.dummyonelink.service.PayAliasService;
import com.psp.dummyonelink.util.PayAliasUtil;
import com.psp.dummyonelink.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PayAliasServiceImpl implements PayAliasService {

    @Autowired
    private PayAliasRepository payAliasRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepo bankRepo;
    @Autowired
    private PayAliasMapper payAliasMapper;

    @Autowired
    private GenericDao<Customer> customerGenericDao;

    @Override
    public CustomResponseEntity createAlias(Long customerId) {

        Customer customer = customerGenericDao.findOneWithQuery(
                "Select c from Customer c where c.id = :id",
                Map.of("id", customerId));

        if (customer == null) {
            return CustomResponseEntity.error("customer not found against this customer ID");
        }
        if (customer.getPayAlias() != null) {
            return CustomResponseEntity.error("alias already created!");
        }

        Set<String> existingPayIds = new HashSet<>(payAliasRepository.findAll1PayIds());

        String unique1PayId = generateAndValidateUnique1PayId(existingPayIds);

        if (customer != null && customer.getPayAlias() == null) {
            PayAlias alias = new PayAlias();
            alias.setOnePayId(unique1PayId);
            alias.setStatus("ACTIVE");
            alias.setCreationDate(LocalDate.now());
            alias.setCustomer(customer);
            payAliasRepository.save(alias);
            return new CustomResponseEntity("alias created successfully");
        }
        return new CustomResponseEntity("customer not present!");
    }

    @Override
    public CustomResponseEntity processTransaction(PayAliasTransactionDto transactionDto) {

        Optional<Bank> exists = Optional.ofNullable(bankRepo.findByBankCode(transactionDto.getBankCode()));
        if (!exists.isPresent()) {
            return new CustomResponseEntity(404, "bank not found!");
        }

        PayAlias payAlias = payAliasRepository.findByOnePayId(transactionDto.getOnePayId());

        if (payAlias == null || !"ACTIVE".equalsIgnoreCase(payAlias.getStatus())) {
            return CustomResponseEntity.error("Invalid or inactive 1PAY-ID");

        }

        Customer customer = payAlias.getCustomer();

        if (transactionDto.getAmount() <= 0) {
            return CustomResponseEntity.error("Invalid transaction amount");
        }

        Account accountToCredit = accountRepository.findByCustomer_Id(customer.getId());
        if (accountToCredit == null) {
            return CustomResponseEntity.error("Account to credit not found");
        }

        String normalizedDbTitle = StringUtils.normalize(accountToCredit.getAccountTitle());
        String normalizedProvidedTitle = StringUtils.normalize(transactionDto.getAccountTitle());
        if(!normalizedDbTitle.equals(normalizedProvidedTitle)){
            throw new ResourceNotFoundException("customer title not matched");
        }

        accountToCredit.setBalance(accountToCredit.getBalance()+transactionDto.getAmount());
        accountRepository.save(accountToCredit);

        OneLinkTransaction transaction  = new OneLinkTransaction();
        transaction.setToAccount(accountToCredit);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionPurpose(transactionDto.getPurpose());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setBank(exists.get());
        transactionRepository.save(transaction);
        return new CustomResponseEntity("transaction completed successfully!");
        }


        public static String generateAndValidateUnique1PayId (Set < String > existingIds) {
            String unique1PayId;
            do {
                unique1PayId = PayAliasUtil.generateUnique1PayId();
            } while (existingIds.contains(unique1PayId));

            return unique1PayId;
        }
    }
