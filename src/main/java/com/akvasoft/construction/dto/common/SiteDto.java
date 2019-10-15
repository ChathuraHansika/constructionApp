package com.akvasoft.construction.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteDto {
    private Integer siteId;
    private ClientDto client;
    private String projectId;
    private String projectName;
    private String description;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String projectType;
    private String vatAdded;
}
