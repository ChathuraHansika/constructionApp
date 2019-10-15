package com.akvasoft.construction.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Integer clientId;
    private String name;
    private String addressLine;
    private String tel;
    private String city;
    private String country;
    private String remarks;
}
