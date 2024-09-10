package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.Bank;
import com.psp.dummyonelink.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("SELECT c from Customer c where c.email = :email AND c.bank.bankCode = :bankCode")
    Optional<Customer> findByEmailAndBank(String email, String bankCode);

    @Query("SELECT c from Customer c where c.cnic = :cnic AND c.bank.bankCode = :bankCode")
    Customer findByCnicAndBank(String cnic, String bankCode);

    Customer findByEmail(String email);
}
