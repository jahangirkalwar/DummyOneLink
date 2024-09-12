package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.email = :email AND c.bank.bankCode = :bankCode")
    Optional<Customer> findByEmailAndBank(
            @Param("email") String email,
            @Param("bankCode") String bankCode
    );

    @Query("SELECT c from Customer c where c.cnic = :cnic AND c.bank.bankCode = :bankCode")
    Customer findByCnicAndBank(String cnic, String bankCode);

    Customer findByEmail(String email);
}
