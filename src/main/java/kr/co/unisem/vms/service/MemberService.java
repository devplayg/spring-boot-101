package kr.co.unisem.vms.service;

import kr.co.unisem.vms.code.EnumRole;
import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.entity.MemberRole;
import kr.co.unisem.vms.exception.ResourceNotFoundException;
import kr.co.unisem.vms.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

//    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        memberRepository.findOne()
//        Member member = memberRepository.findById(memberID)
//                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("member", "username", username));
//        member.map
//        member.
        log.info("member: {}", member.toString());

        String[] list = member.getRoleEnumList().stream().map(role -> role.getKey()).toArray(String[]::new);
//        log.info("member role list: size={}, {}", list.length, list.toString());
//        String[] list = {"Admin", "User"};

//        List<EnumRole.Role> list =  member.getRoleEnumList();
//        Arrays.stream(member.getRoleEnumList())

//        Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);

//        Arrays.stream(member.getRoleList())
//        Arrays.stream(member.getRoleList()).toArray(String[]::new);
//        GrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
//        Arrays.stream(member.getRoleList().toArray()).map(EnumRole::).toArray(String[]::new);
//        Arrays.stream(member.getRoleList().toArray())
//        EnumUtils.getEnumList(State.class)
//        EnumUtils.getEnumMap(enumClass).keySet()
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(list);
        UserDetails userDetails = (UserDetails) new User(member.getUsername(), member.getPassword(), authorities);

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
