package com.devplayg.vms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SecurityController {

//    @Autowired
//    private InMemoryUserDetailsManager inMemoryUserDetailsManager;


    @Autowired
    private SessionRegistry sessionRegistry;

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

    // 자신에게 권한 추가
    @GetMapping("addRole/{role}")
    public String active(@PathVariable("role") String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> roles = new ArrayList<>(auth.getAuthorities());
        roles.add( new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), roles);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "";
    }

    // 사용자에게 권한 추가
    @GetMapping("assign/rule/{username}/{role}")
    public String assignRole(@PathVariable("username") String username, @PathVariable("role") String role) {
//        inMemoryUserDetailsManager.createUser(new User(username, password, new ArrayList<GrantedAuthority>()));
//        return "added";

        List<UserDetails> list = sessionRegistry.getAllPrincipals()
                .stream()
                .map(e -> (UserDetails) e)
                .collect(Collectors.toList());

        for (UserDetails user : list) {
            if (user.getUsername().equals(username)) {
                List<GrantedAuthority> roles = new ArrayList<>(user.getAuthorities());
                roles.add( new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
                Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), roles);
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(user, false);

                log.info("sessions: {}", sessions.toString());
                for (SessionInformation s : sessions) {
                }
            }
        }
        return "";
    }

    @GetMapping("readRole")
    public ResponseEntity<?> readRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> roles = new ArrayList<>(auth.getAuthorities());
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("loggedUsers")
    public ResponseEntity<?> getLoggedUser() {
        List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
        log.info("logged: {}", loggedUsers);
        return new ResponseEntity<>(loggedUsers, HttpStatus.OK);
    }

//    @GetMapping("loggedUsers2")
//    public ResponseEntity<?> readRole(@PathVariable("username") String username) {
//        final List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
//        log.info("logged: {}", loggedUsers);
//        return new ResponseEntity<>(loggedUsers, HttpStatus.OK);
//        List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
//        return new ResponseEntity<>(loggedUsers, HttpStatus.OK);
//        SecurityContextHolder.getContext().getAuthentication()

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        List<GrantedAuthority> roles = new ArrayList<>(auth.getAuthorities());
//        return new ResponseEntity<>(roles, HttpStatus.OK);
//    }
//        updatedAuthorities.add(...); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
//
//
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
//
//        SecurityContextHolder.getContext().setAuthentication(newAuth);

//        List<Object> loggedUsers = sessionRegistry.getAllPrincipals();


        // user object = User currently updated
// invalidate user session
        // https://stackoverflow.com/questions/9910252/how-to-reload-authorities-on-user-update-with-spring-security
//        List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
//        for (Object principal : loggedUsers) {
//            if(principal instanceof User) {
//                final User loggedUser = (User) principal;
//                if(user.getUsername().equals(loggedUser.getUsername())) {
//                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
//                    if(null != sessionsInfo && sessionsInfo.size() > 0) {
//                        for (SessionInformation sessionInformation : sessionsInfo) {
//                            LOGGER.info("Exprire now :" + sessionInformation.getSessionId());
//                            sessionInformation.expireNow();
//                            sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
//                            // User is not forced to re-logging
//                        }
//                    }
//                }
//            }
//        }
//        inMemoryUserDetailsManager.
//        return "";
//    }


//  [
//    {
//            "username":"jisan",
//            "authorities":[
//        {
//            "authority":"ROLE_ADMIN"
//        },
//        {
//            "authority":"ROLE_SHERIFF"
//        },
//        {
//            "authority":"ROLE_USER"
//        }
//      ],
//        "accountNonExpired":true,
//            "accountNonLocked":true,
//            "credentialsNonExpired":true,
//            "enabled":true
//    },
//    {
//            "username":"wsahn",
//            "authorities":[
//        {
//            "authority":"ROLE_ADMIN"
//        },
//        {
//            "authority":"ROLE_USER"
//        }
//      ],
//        "accountNonExpired":true,
//            "accountNonLocked":true,
//            "credentialsNonExpired":true,
//            "enabled":true
//    }
//]
}
