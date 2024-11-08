package com.psp.dummyonelink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RemittanceCustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String country;
    private String city;
    private String NIC;
    private String bankSwiftCode;
    private LocalDateTime createdAt;
}
