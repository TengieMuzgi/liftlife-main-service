package com.liftlife.liftlife.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
    @GetMapping("/private")
    public String privateEndpoint() {
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication1.getPrincipal() ;
        System.out.println(authentication1.getPrincipal());
        System.out.println(authentication1.getCredentials());
        System.out.println(authentication1.getAuthorities());
        System.out.println(authentication1.getDetails());
        return "Hello, " + username + "! This is a private endpoint.";
    }
}
