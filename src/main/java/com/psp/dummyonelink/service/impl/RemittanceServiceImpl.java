package com.psp.dummyonelink.service.impl;

import com.psp.dummyonelink.exception.CustomResponseEntity;
import com.psp.dummyonelink.model.dto.BeneficiaryDto;
import com.psp.dummyonelink.model.dto.RemittanceCustomerDto;
import com.psp.dummyonelink.model.entity.RemittanceAccount;
import com.psp.dummyonelink.model.entity.RemittanceCustomer;
import com.psp.dummyonelink.model.entity.SwiftBank;
import com.psp.dummyonelink.repo.RemittanceAccountRepo;
import com.psp.dummyonelink.repo.RemittanceCustomerRepo;
import com.psp.dummyonelink.repo.SwiftBankRepo;
import com.psp.dummyonelink.service.RemittanceService;
import com.psp.dummyonelink.util.AccountUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RemittanceServiceImpl implements RemittanceService {

    @Autowired
    private RemittanceCustomerRepo remittanceCustomerRepo;

    @Autowired
    private RemittanceAccountRepo remittanceAccountRepo;

    @Autowired
    private SwiftBankRepo swiftBankRepo;
    @Override
    public CustomResponseEntity createCustomer(RemittanceCustomerDto dto) {

        if(dto==null){
            return CustomResponseEntity.error("data can not be null");
        }
        checkExistingData(dto);

        RemittanceCustomer customer = new RemittanceCustomer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setNIC(dto.getNIC());
        customer.setCountry(dto.getCountry());
        customer.setCity(dto.getCity());
        customer.setAddress(dto.getAddress());
        customer.setCreatedAt(LocalDateTime.now());


        SwiftBank bank = swiftBankRepo.findByBankSwiftCode(dto.getBankSwiftCode());
        if(bank==null){
            return CustomResponseEntity.error("bank not found against this swiftCode");
        }
        RemittanceAccount account = new RemittanceAccount();
        account.setSwiftBank(bank);
        account.setAccountNumber(AccountUtil.generateSwiftBankAccountNumber(dto.getBankSwiftCode()));
        account.setCreatedAt(LocalDateTime.now());
        account.setStatus("ACTIVE");
        account.setBalance(0.00);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        remittanceCustomerRepo.save(customer);

        RemittanceCustomerDto remittanceCustomerDto = mapToDo(customer);


        return new CustomResponseEntity(remittanceCustomerDto,"customer created successfully!");
    }

    @Override
    public CustomResponseEntity confirmBeneficiaryDetails(BeneficiaryDto beneficiaryDto) {

        if(
        beneficiaryDto.getAccountNumber().isEmpty() || beneficiaryDto.getAccountNumber().isBlank() ||
        beneficiaryDto.getBankSwiftCode().isEmpty() || beneficiaryDto.getBankSwiftCode().isBlank()){
            return CustomResponseEntity.error("accountNumber or swiftCode can not be empty.");
        }

        BeneficiaryDto beneficiaryDetails = remittanceAccountRepo.findBeneficiaryDetails(beneficiaryDto.getAccountNumber()
                , beneficiaryDto.getBankSwiftCode());

        if(beneficiaryDetails!=null){
            return new CustomResponseEntity(beneficiaryDetails, "beneficiary details!");
        }

        return CustomResponseEntity.error("invalid beneficiary data");

    }

    private RemittanceCustomerDto mapToDo(RemittanceCustomer customer) {
        RemittanceCustomerDto dto = new RemittanceCustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setCity(customer.getCity());
        dto.setCountry(customer.getCountry());
        dto.setAddress(customer.getAddress());
        dto.setNIC(customer.getNIC());
        dto.setEmail(customer.getEmail());
        dto.setBankSwiftCode(dto.getBankSwiftCode());
        dto.setPhone(customer.getPhone());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }

    private void checkExistingData(RemittanceCustomerDto dto) {
        if (remittanceCustomerRepo.existsByNIC(dto.getNIC())) {
            throw new ServiceException("An account with this CNIC already exists");
        }
        if (remittanceCustomerRepo.existsByEmail(dto.getEmail())) {
            throw new ServiceException("An account with this email already exists");
        }
        if (remittanceCustomerRepo.existsByPhone(dto.getPhone())) {
            throw new ServiceException("An account with this phone number already exists");
        }
    }
}
