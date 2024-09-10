package com.psp.dummyonelink.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Bank {

    @Id
    private String bankCode;
    private String bankName;
    private String bankDesc;
    private String country;
    private String state;
    private String city;

    private String secretKey;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Customer> customers;
    @ManyToOne
    @JoinColumn(name = "one_link_id")
    private OneLink oneLink;

}
