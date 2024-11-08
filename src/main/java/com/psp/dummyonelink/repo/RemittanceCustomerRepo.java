package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.RemittanceCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceCustomerRepo extends JpaRepository<RemittanceCustomer,Long> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String email);
    boolean existsByNIC(String nic);
}
