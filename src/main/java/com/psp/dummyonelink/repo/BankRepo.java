package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankRepo extends JpaRepository<Bank, String> {

    @Query("SELECT a FROM Bank a WHERE a.bankCode = :bankCode")
    Bank findByBankCode(@Param("bankCode") String bankCode);

    Bank findBySecretKeyAndBankCode(String secretKey,String bankCode);
}
