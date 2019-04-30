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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // 화면 (등록)
    @GetMapping("add")
    public String index(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "member/add";
    }

    // 화면 (수정)
    @GetMapping("edit/{id}")
    public String index(@PathVariable("id") long memberID, Model model) {
        Optional<Member> member = memberRepository.findById(memberID);
        if (member.isPresent()) {
            model.addAttribute("member", member.get());
        } else {

        }
        return "member/edit";
    }

    // 조회 - 목록
    @GetMapping("/members")
    public ResponseEntity<List<Member>> list(@ModelAttribute("filter") MemberFilter filter) {
        List<Member> list = memberRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 조회 - 단일
//    @GetMapping("/member/{id}")
//    public ResponseEntity<DbResult> get(@ModelAttribute("filter") MemberFilter filter) {
//        Optional<Article> article = memberRepository.findById(articleID);
//        if (article.isPresent()) {
//            return ResponseEntity.ok(new DbResult("", Arrays.asList(article.get())));
//        } else {
//            return ResponseEntity.ok(new DbResult("", null));
//        }
//    }

    // 등록
    @PostMapping
    public ResponseEntity<DbResult> post(@ModelAttribute Member member) {

        // 사용자 권한, 상위객체 설정
        if (member.getRoleList() != null && !member.getRoleList().isEmpty()) {
            for (MemberRole r : member.getRoleList()) {
                r.setMember(member);
            }
        }

        // 사용자 비밀번호 상위객체 설정
        member.getPassword().setMember(member);

        DbResult rs = new DbResult("", 0);
        try {
            Member result = memberRepository.save(member);
            rs.setTotal(1);

        } catch (Exception e) {
            rs.setError(e.getMessage());
        } finally {

        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    // 수정
    @PutMapping
    public ResponseEntity<DbResult> put(@ModelAttribute Member member) {
        log.info("member: {}", member.toString());
//        member.setName();

        DbResult rs = new DbResult("", 0);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    // 삭제
}
