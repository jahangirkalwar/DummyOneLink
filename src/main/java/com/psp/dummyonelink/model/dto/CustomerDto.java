package com.psp.dummyonelink.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String mobileNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "CNIC is required")
    @Size(min = 13,message = "cnic must be 13 digit")
    private String cnic;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    private String registeredDate;
    @NotBlank
    private String country;

    @NotBlank
    private String bankCode;

}
