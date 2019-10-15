package com.akvasoft.construction.dto.common;

import java.io.Serializable;
import java.util.List;

public class UserPrivilegeDto implements Serializable {
    private static final long serialVersionUID = -1301038895125349691L;

    private Integer userID;
    private String userRole;
    private List<String> privileges;
    private String userName;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserPrivilegeDto{" +
                "userID=" + userID +
                ", userRole='" + userRole + '\'' +
                ", privileges=" + privileges +
                ", userName='" + userName + '\'' +
                '}';
    }
}
