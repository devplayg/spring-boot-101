package kr.co.unisem.vms.service;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        memberRepo.findByUsername()
        Member member = memberRepo.findByUsername(username);
//        GrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN", "USER");
        UserDetails userDetails = (UserDetails) new User(member.getUsername(), member.getPassword().getPassword(), authorities);

        return userDetails;
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
