package kr.co.unisem.vms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
//@RequestMapping("sec")
public class SecurityController {

//    @Autowired
//    private SecurityService securityService;

    @GetMapping("/sec/read")
    @PreAuthorize("hasAnyRole('ADMIN','SHERIFF', 'USER')")
    public String read() {
        return "read";
    }

    @GetMapping("/sec/edit")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SHERIFF')")
    public String audit() {
        return "edit";
    }

    @GetMapping("/sec/write")
    @PreAuthorize("hasRole('ADMIN')")
    public String write(HttpServletRequest req, HttpServletResponse res, Object handler) {
        log.info("in admin:   {}", req.isUserInRole("ADMIN"));
        log.info("in sheriff: {}", req.isUserInRole("SHERIFF"));
        log.info("in user:    {}", req.isUserInRole("USER"));
        log.info("in admin:   {}", req.isUserInRole("ROLE_ADMIN"));
        log.info("in sheriff: {}", req.isUserInRole("ROLE_SHERIFF"));
        log.info("in user:    {}", req.isUserInRole("ROLE_USER"));
        return "write";
    }
}
