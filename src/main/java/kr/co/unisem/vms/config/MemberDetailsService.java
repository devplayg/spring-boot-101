package kr.co.unisem.vms.config;

import java.util.Arrays;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByUsername(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
        UserDetails userDetails = (UserDetails) new User(member.getUserName(), member.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}

