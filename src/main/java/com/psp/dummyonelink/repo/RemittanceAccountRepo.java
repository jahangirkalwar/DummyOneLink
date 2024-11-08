package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.dto.BeneficiaryDto;
import com.psp.dummyonelink.model.entity.RemittanceAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RemittanceAccountRepo extends JpaRepository<RemittanceAccount,Long> {


    @Query("SELECT new com.psp.dummyonelink.model.dto.BeneficiaryDto(a.accountNumber, b.bankSwiftCode, b.name, CONCAT(c.firstName, ' ', c.lastName), c.address) " +
            "FROM RemittanceAccount a " +
            "JOIN a.swiftBank b " +
            "JOIN a.customer c " +
            "WHERE a.accountNumber = :accountNumber " +
            "AND b.bankSwiftCode = :bankSwiftCode")
    BeneficiaryDto findBeneficiaryDetails(@Param("accountNumber") String accountNumber,
                                                    @Param("bankSwiftCode") String bankSwiftCode);




}

