package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.SwiftDto;
import com.psp.dummyonelink.model.entity.Swift;
import com.psp.dummyonelink.repo.SwiftRepo;
import com.psp.dummyonelink.service.SwiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwiftServiceImpl implements SwiftService {

    @Autowired
    private SwiftRepo swiftRepo;
    @Override
    public CustomResponseEntity createSwift(String code) {
        if(code.isEmpty()){
            return CustomResponseEntity.error("code can not be null");
        }

        Swift exists = swiftRepo.findByCode(code);
        if(exists==null){
        Swift swift = new Swift();
        swift.setCode(code);
        Swift savedSwift = swiftRepo.save(swift);
        SwiftDto swiftDto = mapToDto(savedSwift);
        return new CustomResponseEntity(swiftDto, "swift added");}

        return CustomResponseEntity.error("already exists!");
    }

    private SwiftDto mapToDto(Swift swift) {

        SwiftDto dto = new SwiftDto();
        dto.setId(swift.getId());
        dto.setCode(swift.getCode());
        return dto;
    }
}
