package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.exception.ResourceNotFoundException;
import com.psp.dummyonelink.model.dto.*;
import com.psp.dummyonelink.model.entity.Account;
import com.psp.dummyonelink.model.entity.Bank;
import com.psp.dummyonelink.model.entity.OneLink;
import com.psp.dummyonelink.model.entity.OneLinkTransaction;
import com.psp.dummyonelink.model.generic.GenericDao;
import com.psp.dummyonelink.model.mapper.OneLinkMapper;
import com.psp.dummyonelink.repo.AccountRepository;
import com.psp.dummyonelink.repo.BankRepo;
import com.psp.dummyonelink.repo.OneLinkRepo;
import com.psp.dummyonelink.repo.TransactionRepository;
import com.psp.dummyonelink.service.BankService;
import com.psp.dummyonelink.service.OneLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class OneLinkServiceImpl implements OneLinkService {


    @Autowired
    private GenericDao<OneLink> genericDao;
    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private OneLinkRepo oneLinkRepo;

    @Autowired
    private OneLinkMapper oneLinkMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;


    private CustomResponseEntity response;

    private final String URL = "http://localhost:8081/account/debitAccount/cnic/mobileNumber/accountNumber";

    private static final Logger LOGGER = LoggerFactory.getLogger(OneLinkServiceImpl.class);


    @Override
    public CustomResponseEntity getPaymentServiceProvider(String name) {

        Optional<OneLink> link = Optional.ofNullable(oneLinkRepo.findOneLinkByName(name));

        if(!link.isPresent()){
            throw new ResourceNotFoundException("Payment Service Provider Not Found with name "+name);
        }

        String jpql = "select ol from OneLink ol where ol.name = :name";

        Map<String,Object> param = new HashMap<>();
        param.put("name",name);
        OneLink oneLink = genericDao.findOneWithQuery(jpql, param);
        OneLinkDto oneLinkDto = oneLinkMapper.jpeToDto(oneLink);
        return new CustomResponseEntity(oneLinkDto,"payment service provider found!");
    }

    @Override
    public CustomResponseEntity fetchAccountTitle(String accountNumber, String bankCode) {

        BankAccountInfoDto bankAccountInfoDto = accountRepository.findAccountTitleByAccountNumberOrIbanCodeAndBank(accountNumber, bankCode);
        Map<String,String> map = new HashMap<>();
        map.put("Account Title",bankAccountInfoDto.getAccountTitle());
        map.put("Bank Name",bankAccountInfoDto.getBankName());
        return new CustomResponseEntity(map,"Account Title");
    }

//    @Override
//    public CustomResponseEntity makeTransaction(TransactionDto transactionDto) {
//
//
//    }

    public static String generateTransactionId() {
        Random random = new Random();
        int id = 10000000 + random.nextInt(90000000);
        return String.valueOf(id);
    }
    public static ResponseTransactionDto toDto(OneLinkTransaction transaction) {

        ResponseTransactionDto dto = new ResponseTransactionDto();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setAmount(transaction.getAmount());
        dto.setPurpose(transaction.getTransactionPurpose());
        dto.setTransactionDate(transaction.getTransactionDate());

        return dto;
    }


    @Override
    public CustomResponseEntity processTransaction(TransactionDto transactionDto) {

        Optional<Bank> exists = Optional.ofNullable(bankRepo.findByBankCode(transactionDto.getBankCode()));
        if (!exists.isPresent()) {
            return new CustomResponseEntity(404, "bank not found against this code!");
        }

        Account toAccount = accountRepository.findByAccountNumberOrIBanCodeAndBank_BankCode(transactionDto.getToAccountNumber(),transactionDto.getBankCode());

        if(toAccount==null){
            return CustomResponseEntity.error("recipient account does not exist");
        }

//        String normalizedDbTitle = StringUtils.normalize(toAccount.getAccountTitle());
//        String normalizedProvidedTitle = StringUtils.normalize(transactionDto.getAccountTitle());
//        if(!normalizedDbTitle.equals(normalizedProvidedTitle)){
//            throw new ResourceNotFoundException("customer title not matched");
//        }

        toAccount.setBalance(toAccount.getBalance()+transactionDto.getAmount());
        accountRepository.save(toAccount);


        OneLinkTransaction transaction  = new OneLinkTransaction();
        transaction.setToAccount(toAccount);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionPurpose(transactionDto.getPurpose());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setBank(exists.get());
        transaction.setTransactionId(generateTransactionId());
        OneLinkTransaction savedTransaction = transactionRepository.save(transaction);

        ResponseTransactionDto responseTransactionDto = toDto(savedTransaction);
        return new CustomResponseEntity(responseTransactionDto,"transaction completed successfully!");
    }

    @Override
    public AccountDetailsDto fetchAccountDetails(String accountNumber, String bankName) {

        Optional<Account> account = Optional.ofNullable(accountRepository
                .findByAccountNumberOrIBanCodeAndBank_BankName(accountNumber, bankName));

        if(account.isEmpty()){
            throw new ResourceNotFoundException("account number or bank code is invalid");
        }

        AccountDetailsDto dto = new AccountDetailsDto();
        dto.setAccountBalance(account.get().getBalance());
        dto.setAccountNumber(accountNumber);
        dto.setAccountStatus(account.get().getAccountStatus());
        dto.setAccountTitle(account.get().getAccountTitle());
        dto.setAccountType(account.get().getAccountType());
        dto.setAccountDescription("Basic saving account");
        dto.setCnicNo(account.get().getCustomer().getCnic());
        dto.setEmail(account.get().getCustomer().getEmail());
        dto.setId(account.get().getId());
        dto.setAccountOpenDate(account.get().getCreatedAt());
        dto.setAccountClosedDate(null);
        dto.setAccountClosedReason(null);
        dto.setProofOfIncome(account.get().getProofOfIncome());
        dto.setBranchCode(account.get().getBank().getBankCode());

        return dto;
    }
}
