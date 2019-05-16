package com.devplayg.vms.service;

import com.devplayg.vms.entity.Member;
import com.devplayg.vms.exception.ResourceNotFoundException;
import com.devplayg.vms.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("member", "username", username));
        log.info("member: {}", member.toString());
//        String[] roles = member.getRoleEnumList().stream().map(role -> role.getKey()).toArray(String[]::new);
//        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
        UserDetails userDetails = (UserDetails) new User(member.getUsername(), member.getPassword(), getAuthorities(member));

        return userDetails;
    }

    private static List<? extends GrantedAuthority> getAuthorities(Member member) {
        String[] roles = member.getRoleList().stream().map(role -> "ROLE_" + role.getRole().getKey()).toArray(String[]::new);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
        return authorities;
    }
//    https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) throw new UsernameNotFoundException(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }


//    @GetMapping("/members")
//    public ResponseEntity<List<Member>> getAllArticles() {
//        List<Member> list = memberRepo.findAll();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
}
