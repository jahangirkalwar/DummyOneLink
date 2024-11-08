package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.Swift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwiftRepo extends JpaRepository<Swift,Long> {

    Swift findByCode (String code);
}
