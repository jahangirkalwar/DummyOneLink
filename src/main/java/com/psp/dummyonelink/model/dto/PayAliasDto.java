package com.psp.dummyonelink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PayAliasDto {

    private Long customerId;
    private String onePayId;
    private String creationDate;
    private String status;
}
