package com.psp.dummyonelink.repo;

import com.psp.dummyonelink.model.entity.OneLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneLinkRepo extends JpaRepository<OneLink,Long> {

    OneLink findOneLinkByName(String name);

}
