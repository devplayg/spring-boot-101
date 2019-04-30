package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.entity.MemberRole;
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
    public ResponseEntity<DbResult> post(@ModelAttribute Member member) {
        log.info("member: {}", member.toString());
        for (MemberRole r : member.getRoleList()) {
            r.setMember(member);
        }
        member.getPassword().setMember(member);
//        log.info("member: {}", member.toString());



        memberRepository.save(member);
        DbResult rs = new DbResult("", 1);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    // 수정

    // 삭제
}
