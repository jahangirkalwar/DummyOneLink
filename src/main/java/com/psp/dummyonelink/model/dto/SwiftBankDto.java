package com.psp.dummyonelink.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SwiftBankDto {

    private Long id;
    private String name;
    private String bankSwiftCode;
    private String country;
    private String city;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long swiftId;
}
