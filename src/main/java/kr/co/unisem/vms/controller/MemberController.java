package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberController implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByUsername(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());

        List<GrantedAuthority> authorities =AuthorityUtils.createAuthorityList("ADMIN", "USER");
        UserDetails userDetails = (UserDetails) new User(member.getUserName(), member.getPassword(), authorities);
        return userDetails;
    }
}
