package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.vo.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AppProperties appProperties;

    // 로그인
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) { // 이미 로그인 된 상태면
            return "redirect:" + appProperties.getHomeuri();
        } else {
            return "login/login";
        }
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
