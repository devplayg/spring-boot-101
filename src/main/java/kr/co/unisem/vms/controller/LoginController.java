package kr.co.unisem.vms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@SpringBootApplication
@Controller
@Slf4j
@ConfigurationProperties("appconf")
public class LoginController {

//    @Value{"${property.homeurl}"}
//    private String homeUrl;

    // 로그인
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) { // 이미 로그인 된 상태면
            return "redirect:/app/articles";
        } else {
            return "login/login";
        }
    }
}
