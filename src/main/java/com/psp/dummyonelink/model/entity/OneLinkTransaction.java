package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OneLinkTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;

    private Double amount;
    private LocalDateTime transactionDate;
    private String transactionPurpose;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;
    @ManyToOne
    @JoinColumn(name = "bankCode", nullable = false)
    private Bank bank;

}
