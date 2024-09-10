package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aliasId;
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private String onePayId;
    private LocalDate creationDate;
    private String status;
}
