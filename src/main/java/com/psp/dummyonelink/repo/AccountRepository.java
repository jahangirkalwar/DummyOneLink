package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.dto.BankAccountInfoDto;
import com.psp.dummyonelink.model.entity.Account;
import com.psp.dummyonelink.model.entity.Bank;
import com.psp.dummyonelink.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query("SELECT a FROM Account a WHERE a.accountNumber = :identifier OR a.ibanCode = :identifier")
    Account findByAccountNumberOrIBanCode(@Param("identifier") String identifier);

    Account findByCustomer_Id(Long customerId);

    @Query("SELECT c FROM Account c WHERE (c.accountNumber = :identifier OR c.ibanCode = :identifier) AND c.bank.bankCode = :bankCode")
    Account findByAccountNumberOrIBanCodeAndBank_BankCode(@Param("identifier") String identifier, @Param("bankCode") String bankName);

    @Query("SELECT a.accountTitle from Account a where a.accountNumber = :accountNumber AND a.bank.bankCode = :bankCode")
    String findAccountTitleByAccountNumberAndBank_BankCode(String accountNumber, String bankCode);

    @Query("SELECT new com.psp.dummyonelink.model.dto.BankAccountInfoDto(a.accountTitle, a.bank.bankDesc) " +
            "from Account a where (a.accountNumber = :identifier OR a.ibanCode = :identifier)" +
            "AND a.bank.bankCode = :bankCode")
    BankAccountInfoDto findAccountTitleByAccountNumberOrIbanCodeAndBank(@Param("identifier") String identifier, @Param("bankCode") String bankCode);

    @Query("SELECT c FROM Account c WHERE (c.accountNumber = :identifier OR c.ibanCode = :identifier) AND c.bank.bankName = :bankName")
    Account findByAccountNumberOrIBanCodeAndBank_BankName(@Param("identifier") String identifier, @Param("bankName") String bankName);

    @Query("SELECT a FROM Account a WHERE a.accountType = :donations")
    List<Account> findAccountByAccountType(@Param("donations") String donations);

//    @Query("SELECT c from Account c where c.accountNumber = :identifier OR c.ibanCode = :identifier AND c.bank.bankName = :bankName")
//    Account findByAccountNumberOrIBanCodeAndBank_BankName(@Param("identifier") String identifier, @Param("bankName") String bankName);


}
