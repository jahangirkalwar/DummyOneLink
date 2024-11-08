package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.SwiftBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwiftBankRepo extends JpaRepository<SwiftBank, Long> {

    SwiftBank findByBankSwiftCode (String bankSwiftCode);
}
