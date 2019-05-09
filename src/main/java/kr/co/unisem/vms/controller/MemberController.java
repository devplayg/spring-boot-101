package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.entity.MemberRole;
import kr.co.unisem.vms.exception.ResourceNotFoundException;
import kr.co.unisem.vms.filter.MemberFilter;
import kr.co.unisem.vms.repository.MemberRepository;
import kr.co.unisem.vms.vo.DbResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
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

    // 화면 (등록)
    @GetMapping("new")
    public String index(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "member/add";
    }

    // 조회
    @GetMapping("{id}")
    public ResponseEntity<Member> get(@PathVariable("id") long memberID) {
        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
        return new ResponseEntity<Member>(member, HttpStatus.OK);
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

    @PatchMapping("{id}")
    public ResponseEntity<?> patch(@ModelAttribute("member") Member input, @PathVariable("id") long memberID) {
        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
        member.setName(input.getName());
        member.setEmail(input.getEmail());
        member.setEnabled(input.isEnabled());
        member.setRoleList(new ArrayList<>());

        // 입력받은 권한을 설정
        for (MemberRole r : input.getRoleList()) {
            if (r.getRole() != null) {
                r.setMember(member);
                member.getRoleList().add(r);
            }
        }
        memberRepository.save(member);
        DbResult rs = new DbResult("", 0);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PatchMapping("patchTest/{id}")
    @Transactional
    public ResponseEntity<?> patchTest(@ModelAttribute("member") Member input, @PathVariable("id") long memberID) {
                Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
        member.setName(input.getName());
        member.setEmail(input.getEmail());
        member.setEnabled(input.isEnabled());
        member.setRoleList(new ArrayList<>());
            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        DbResult rs = new DbResult("", 0);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    // 조회 - 목록
    @GetMapping("members")
    public ResponseEntity<List<Member>> list(@ModelAttribute("filter") MemberFilter filter) {
        List<Member> list = memberRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 등록
    @PostMapping
    public ResponseEntity<?> post(@ModelAttribute Member member) {

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
            log.info("member: {}", member.toString());
            Member result = memberRepository.save(member);
            rs.setTotal(1);

        } catch (Exception e) {
            rs.setError(e.getMessage());
        } finally {

        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }



    // 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> patch(@PathVariable("id") long memberID) {
        memberRepository.deleteById(memberID);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}



