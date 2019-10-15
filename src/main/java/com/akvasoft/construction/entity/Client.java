package com.akvasoft.construction.entity;

import com.akvasoft.construction.dto.common.ClientDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "T_CLIENT")
public class Client implements Serializable {
    private static final long serialVersionUID = -5056165421904904219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Integer clientId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "REMARKS")
    private String remarks;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Client{");
        sb.append("clientId=").append(clientId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", addressLine='").append(addressLine).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public ClientDto getClientDto() {
        ClientDto dto = new ClientDto();
        dto.setAddressLine(this.addressLine);
        dto.setCity(this.city);
        dto.setClientId(this.clientId);
        dto.setCountry(this.country);
        dto.setName(this.name);
        dto.setRemarks(this.remarks);
        dto.setTel(this.tel);
        return dto;
    }
}
