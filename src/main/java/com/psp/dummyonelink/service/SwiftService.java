package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;

public interface SwiftService {

    public CustomResponseEntity createSwift(String code);
}