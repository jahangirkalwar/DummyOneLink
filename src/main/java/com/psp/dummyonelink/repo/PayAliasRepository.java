package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.PayAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayAliasRepository extends JpaRepository<PayAlias, Long> {

    @Query("SELECT a.onePayId FROM PayAlias a")
    List<String> findAll1PayIds();

    PayAlias findByOnePayId(String onePayId);
}
