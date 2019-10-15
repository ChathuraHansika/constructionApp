package com.akvasoft.construction.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = -367781715298055878L;
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleID;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Privilege> privileges;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> list=new ArrayList<>();
        for(Privilege privilege:privileges){
            list.add(privilege);
        }
        return list;

    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("roleID=").append(roleID);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", privileges=").append(privileges);
        sb.append('}');
        return sb.toString();
    }

}
