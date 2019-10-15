package com.akvasoft.construction.controller.common;

import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get-privilege", method = RequestMethod.GET)
    public Response getPrivileges() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Response response = new Response();
        response.setResult(userService.getPrivilages());
        response.setSuccess(true);
        return response;
    }
}
