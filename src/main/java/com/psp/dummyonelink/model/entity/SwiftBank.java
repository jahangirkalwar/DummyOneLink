package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "swift_banks")
public class SwiftBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bankSwiftCode;
    private String country;
    private String city;
    private String address;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "swift_code")
    private Swift swift;

    @OneToMany(mappedBy = "swiftBank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RemittanceAccount> accounts;
}
