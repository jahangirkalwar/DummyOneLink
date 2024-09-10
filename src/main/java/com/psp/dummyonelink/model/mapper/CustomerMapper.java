package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.CustomerDto;
import com.psp.dummyonelink.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto jpeToDto(Customer customer);
    Customer dtoToJpe(CustomerDto customerDto);
}
