package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.CustomerDto;

public interface CustomerService {

    CustomResponseEntity addCustomer(CustomerDto customerDto);
}
