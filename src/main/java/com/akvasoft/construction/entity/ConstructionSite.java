package com.akvasoft.construction.entity;

import com.akvasoft.construction.dto.common.SiteDto;
import com.akvasoft.construction.entity.hrm.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_CONSTRUCTION_SITE")
@Getter
@Setter
public class ConstructionSite implements Serializable {
    private static final long serialVersionUID = -4336465182576986262L;

    @Id
    @Column(name = "CONSTRUCTION_SITE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer siteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @Column(name = "PROJECT_ID")
    private String projectId;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PROJECT_TYPE")
    private String projectType;

    @Column(name = "VAT_ADDED")
    private String vatAdded;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public SiteDto getSiteDto() {
        SiteDto dto = new SiteDto();
        dto.setAddressLine1(this.addressLine1);
        dto.setAddressLine2(this.addressLine2);
        dto.setCity(this.city);
        dto.setCountry(this.country);
        dto.setDescription(this.description);
        dto.setProjectId(this.projectId);
        dto.setProjectName(this.projectName);
        dto.setProjectType(this.projectType);
        dto.setSiteId(this.siteId);
        dto.setVatAdded(this.vatAdded);
        dto.setClient(this.client.getClientDto());
        return dto;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConstructionSite{");
        sb.append("siteId=").append(siteId);
        sb.append(", client=").append(client);
        sb.append(", projectId='").append(projectId).append('\'');
        sb.append(", projectName='").append(projectName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", addressLine1='").append(addressLine1).append('\'');
        sb.append(", addressLine2='").append(addressLine2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", projectType='").append(projectType).append('\'');
        sb.append(", vatAdded='").append(vatAdded).append('\'');
        sb.append(", employees=").append(employees);
        sb.append('}');
        return sb.toString();
    }
}
