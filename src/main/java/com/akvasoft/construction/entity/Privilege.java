package com.akvasoft.construction.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "T_PRIVILEGE")
public class Privilege implements GrantedAuthority {
    @Id
    @Column(name = "PRIVILEGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer privilegeId;

    @Column(name = "NAME")
    private String name;


    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getCode() {
        return name;
    }

    public void setCode(String code) {
        this.name = code;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Privilege{");
        sb.append("privilegeId=").append(privilegeId);
        sb.append(", code='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
