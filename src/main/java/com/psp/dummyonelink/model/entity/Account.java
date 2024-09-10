package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountTitle;
    private String accountStatus;
    private String accountType;
    private Double balance;
    private String ibanCode;
    private Date createdAt;
    private Date closedAt;
    private String accountClosedReason;
    private String proofOfIncome;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL)
    private List<OneLinkTransaction> receivedTransactions;
}
