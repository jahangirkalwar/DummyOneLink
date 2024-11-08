package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RemittanceAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Double balance;
    private String status;
    @CreatedDate
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "swift_bank_id", nullable = false)
    private SwiftBank swiftBank;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private RemittanceCustomer customer;

}
