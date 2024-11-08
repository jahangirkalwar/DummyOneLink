package com.psp.dummyonelink.service;

import com.psp.dummyonelink.exception.CustomResponseEntity;

public interface CurrencyConversionService {

    public CustomResponseEntity convertCurrency(String fromCurrency, String toCurrency, double amount);
}
