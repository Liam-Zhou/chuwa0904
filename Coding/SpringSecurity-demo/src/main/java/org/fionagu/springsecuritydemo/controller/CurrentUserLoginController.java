package org.fionagu.springsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class CurrentUserLoginController {
    @GetMapping("/getLoginUser")
    @PreAuthorize("hasAuthority('User')")
    public Authentication getLoginUser(Authentication authentication){
        return authentication;
    }

    /*安全上下文*/
    @GetMapping("/getLoginUser2")
    @PreAuthorize("hasAuthority('Admin')")
    public Principal getLoginUser2(){
        //安全上下文持有器获取上下文，再获取认证信息
        return  SecurityContextHolder.getContext().getAuthentication();
    }

    // Method-Level Security
    //expects User authority
    @GetMapping("/getLoginUser3")
    @PreAuthorize("hasAuthority('User')")
    // if a request doesn't pass the URL-level security filters, it will be blocked, and the method-level security will never even be reached.
    public Principal getLoginUser3(){
        //安全上下文持有器获取上下文，再获取认证信息
        return  SecurityContextHolder.getContext().getAuthentication();
    }
}
