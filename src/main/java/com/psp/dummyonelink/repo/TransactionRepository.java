package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.OneLinkTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<OneLinkTransaction,Long> {
}
