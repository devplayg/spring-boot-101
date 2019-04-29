package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.code.EnumRole;
import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.filter.MemberFilter;
import kr.co.unisem.vms.repository.MemberRepository;
import kr.co.unisem.vms.vo.DbResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // 화면
    @GetMapping
    public String index(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "member/member";
    }

    // 조회
    @GetMapping("/members")
    public ResponseEntity<List<Member>> list(@ModelAttribute("filter") MemberFilter filter) {
        List<Member> list = memberRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 등록
    @PostMapping
    public ResponseEntity<DbResult> post(@ModelAttribute Member member, Model model) {
//        member.updateRoles();
        log.info("member: {}", member.toString());
        DbResult rs = new DbResult("", 1);
        return new ResponseEntity<>(rs, HttpStatus.OK);


//        Member m = new Member();
//        m.setEmail(member.getEmail());
//        m.setName(member.getName());
//        m.setEnabled(m.isEnabled());
//        m.setUsername(member.getUsername());
//        MemberPassword pw = new MemberPassword(member.getPassword().getPassword());
//        m.addToPassword(pw);
//        MemberRole r = new MemberRole("Admin");
//        m.addToRole(r);



//        m.setPassword(member.getEmail());
//        log.info("member: {}", member.toString());
//        log.info("password: {}", member.getPassword().toString());
//        memberRepository.save(m);
//        memberRepository.save(member);
//        DbResult rs = new DbResult("", 1);
//        return new ResponseEntity<>(rs, HttpStatus.OK);





    }


    // 수정

    // 삭제
}
