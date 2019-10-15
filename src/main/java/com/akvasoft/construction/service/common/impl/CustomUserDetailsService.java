/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.akvasoft.construction.service.common.impl;

import com.akvasoft.construction.dto.common.UserPrivilegeDto;
import com.akvasoft.construction.entity.Privilege;
import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.exception.ConstructionRuntimeException;
import com.akvasoft.construction.exception.ExceptionConstant.ExceptionType;
import com.akvasoft.construction.repo.UserRepo;
import com.akvasoft.construction.service.common.UserService;
import com.akvasoft.construction.service.hrm.HRService;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetail")
public class CustomUserDetailsService implements UserDetailsService, UserService {

    private final UserRepo userRepository;

    @Autowired
    private HRService service;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepository) {

        this.userRepository = userRepository;
    }

    @Cacheable(value = "user", key = "#username")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameAndStatus(username, DomainConstant.Status.ACT);
        if (user == null) {
            throw new ConstructionRuntimeException(ExceptionType.NO_PRIVILEGE);
        }
        return user;
    }

    @Override
    public List<String> getUserPrivileges() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((Collection<Privilege>) auth.getAuthorities()).stream().map(Privilege::getCode).collect(Collectors.toList());
    }

    @Override
    public String getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((User) authentication.getPrincipal()).getRole().getRoleName();
    }

    @Override
    public UserPrivilegeDto getPrivilages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrivilegeDto dto = new UserPrivilegeDto();
        dto.setPrivileges(getUserPrivileges());
        dto.setUserRole(getUserRole());
        dto.setUserID(((User) authentication.getPrincipal()).getUserId());
        dto.setUserName(((User) authentication.getPrincipal()).getUserName());
        return dto;
    }
}
