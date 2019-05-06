package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.entity.MemberRole;
import kr.co.unisem.vms.exception.ResourceNotFoundException;
import kr.co.unisem.vms.filter.MemberFilter;
import kr.co.unisem.vms.repository.MemberPasswordRepository;
import kr.co.unisem.vms.repository.MemberRepository;
import kr.co.unisem.vms.repository.MemberRoleRepository;
import kr.co.unisem.vms.vo.DbResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    private MemberPasswordRepository memberPasswordRepository;

    // 화면 (등록)
    @GetMapping("new")
    public String index(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "member/add";
    }

    // 화면 (수정)
    @GetMapping("edit/{id}")
    public String index(@PathVariable("id") long memberID, Model model) {
        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
        log.info("member: {}", member.toString());
        model.addAttribute("member", member);
        return "member/edit";
    }

    // 수정
//    @PatchMapping("{memberID}")
//    public ResponseEntity<DbResult> patch(@ModelAttribute("member") Member member, @PathVariable("memberID") long memberID) {
//        log.info("memberID: {}", memberID);
//        log.info("member: {}", member.toString());
//
//        memberRepository.save(member);
//
//        DbResult rs = new DbResult("", 0);
//        return new ResponseEntity<>(rs, HttpStatus.OK);
//    }

    @PatchMapping("{memberID}")
    public ResponseEntity<DbResult> updateMember(@ModelAttribute("member") Member input, @PathVariable("memberID") long memberID) {
        log.info("input: {}", input.toString());
//        Optional<Member> memberOpt = memberRepository.findById(memberID);
//        if (memberOpt.isPresent()) {
//            Member member = memberOpt.get();
//            log.info("member: {}", member.toString());
//            member.setName(input.getName());
//            member.setEmail(input.getEmail());
//            member.setEnabled(input.isEnabled());
//            log.info("member: {}", member.toString());
//            memberRepository.save(input);
//            rs.setAffectedRows(1);
//
//        } else {
//
//        }


        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", memberID));
        member.setName(input.getName());
        member.setEmail(input.getEmail());
        member.setEnabled(input.isEnabled());
        memberRepository.save(member);


//        memberRoleRepository.deleteAll();
        memberRoleRepository.deleteAll(member.getRoleList());
//        memberRoleRepository.flush();
//        memberRoleRepository.sa
            List<MemberRole> list = new ArrayList<>();
            for (MemberRole r : input.getRoleList()) {
                if (r.getRole() != null) {
                    r.setMember(member);
                    list.add(r);
                }
            }
            if (list.size() > 0) {
                memberRoleRepository.saveAll(list);
            }

//        memberRoleRepository.saveAll(input.getRoleList());


        DbResult rs = new DbResult("", 0);
//        Optional<Member> memberOpt = memberRepository.findById(memberID);
//        if (memberOpt.isPresent()) {
//            Member member = memberOpt.get();
//        member.setName(input.getName());
//        member.setEmail(input.getEmail());
//            rs.setAffectedRows(1);
//
//        } else {
//
//        }

//        memberRepository.save(input);

//        log.info("memberID: {}", memberID);
//        log.info("input: {}", input.toString());
//                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", memberID));


//        Member changed = memberRepository.save(member);
//        return changed;

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    // 조회 - 목록
    @GetMapping("members")
    public ResponseEntity<List<Member>> list(@ModelAttribute("filter") MemberFilter filter) {
        List<Member> list = memberRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //
//    @GetMapping("/notes/{id}")
//    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
//        return noteRepository.findById(noteId)
//                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
//    }


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


    // 삭제
}



