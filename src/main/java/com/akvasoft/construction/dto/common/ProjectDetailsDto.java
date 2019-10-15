package com.akvasoft.construction.dto.common;

import com.akvasoft.construction.entity.Client;
import com.akvasoft.construction.entity.ConstructionSite;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter

public class ProjectDetailsDto {
    String projectId;
    String projectName;
    String projectType;
    String projectAddressOne;
    String projectAddressTwo;
    String city;
    String description;
    String vatAdded;
    String country;
    String clientName;
    String clientAddress;
    String clientTel;
    String clientCity;
    String clientCountry;
    String remark;


    public Client getClientEntity() {
        Client client = new Client();
        client.setName(this.clientName);
        client.setAddressLine(this.clientAddress);
        client.setCity(this.clientCity);
        client.setCountry(this.clientCountry);
        client.setRemarks(this.remark);
        client.setTel(this.clientTel);
        return client;
    }

    public ConstructionSite getSiteEntity(Client client) {
        ConstructionSite site = new ConstructionSite();
        site.setClient(client);
        site.setCity(this.city);
        site.setAddressLine1(this.projectAddressOne);
        site.setAddressLine2(this.projectAddressTwo);
        site.setDescription(this.description);
        site.setVatAdded(this.vatAdded);
        site.setProjectName(this.projectName);
        site.setCountry(this.country);
        site.setProjectType(this.projectType);
        site.setProjectId(this.projectId);
        return site;
    }


}
