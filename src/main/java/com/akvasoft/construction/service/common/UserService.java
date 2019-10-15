package com.akvasoft.construction.service.common;

import com.akvasoft.construction.dto.common.UserPrivilegeDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    List<String > getUserPrivileges();
    String getUserRole();
    UserPrivilegeDto getPrivilages();
}
