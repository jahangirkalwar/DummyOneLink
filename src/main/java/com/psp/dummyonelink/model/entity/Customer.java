package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String cnic;
    private String dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String country;
    private String registeredDate;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private PayAlias payAlias;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;


}
