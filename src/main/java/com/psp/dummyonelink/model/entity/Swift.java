package com.psp.dummyonelink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Swift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @OneToMany(mappedBy = "swift",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<SwiftBank> swiftBanks;


}
