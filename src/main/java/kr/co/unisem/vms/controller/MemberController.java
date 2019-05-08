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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

//    @PersistenceContext
//    EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

//    @Autowired
//    private MemberRoleRepository memberRoleRepository;
//
//    @Autowired
//    private MemberPasswordRepository memberPasswordRepository;

    // 화면 (등록)
    @GetMapping("new")
    public String index(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "member/add";
    }

    // 조회
    @GetMapping("{memberID}")
    public ResponseEntity<Member> get(@PathVariable("memberID") long memberID) {
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


//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
//    public void updateMember(Member member, Member input) {
//        member.setName(input.getName());
//        member.setEmail(input.getEmail());
//        member.setEnabled(input.isEnabled());
//        memberRepository.save(member);
//
//        if (input.getRoleList().size() > 0) {
//            this.updateMemberRole(member, input);
//        }
//    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    public void update(Member member, Member input) throws RollbackException {
//        member.setName(input.getName());
//        member.setEmail(input.getEmail());
//        member.setEnabled(input.isEnabled());
//        Member result = memberRepository.save(member);
//        if (result == null) throw new RollbackException();
//
//        memberRoleRepository.deleteAll(member.getRoleList());
////        if (resultList== null) throw new RollbackException();
//
//        List<MemberRole> list = new ArrayList<>();
//        for (MemberRole r : input.getRoleList()) {
//            if (r.getRole() != null) {
//                r.setMember(input);
//                list.add(r);
//            }
//        }
//
//        if (list.size() > 0) {
//            memberRoleRepository.saveAll(list);
//        }
//    }

//    @Transactional( rollbackOn = RollbackException.class)
//    public void save(ExObj user, ExObj2 userDetails) throws RollBackException {
//
//        ExObj resUser =   rollBackRepository.save(user);
//        if(resUser == null) throw new RollBackException();
//
//        ExObj2 resUserDetails = rollBackRepository.save(userDetails);
//        if(resUserDetails == null) throw new RollBackException();
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
//    public void updateMemberRole(Member member, Member input) {
//        log.info("### Delete");
//        memberRoleRepository.deleteAll(member.getRoleList());
//        log.info("member: {}", member);
//        log.info("### end of Delete");
//        List<MemberRole> list = new ArrayList<>();
//        for (MemberRole r : input.getRoleList()) {
//            if (r.getRole() != null) {
//                r.setMember(input);
//                list.add(r);
//            }
//        }
//
//        if (list.size() > 0) {
//            memberRoleRepository.saveAll(list);
//        }
//    }


    @PatchMapping("{memberID}")
    public ResponseEntity<?> patch(@ModelAttribute("member") Member input, @PathVariable("memberID") long memberID) {
        log.info("input: {}", input.toString());

        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
        member.setName(input.getName());
        member.setEmail(input.getEmail());
        member.setEnabled(input.isEnabled());
//        member.setRoleList(null);
        List<MemberRole> list = member.getRoleList().stream().filter(role -> {
//            role.setMember(null);
            return false; // Delete !
        }).collect(Collectors.toList());

//        member.setRoleList(null);
//        log.info("member before flush: {}", member.toString());
//        memberRepository.saveAndFlush(member);

        // 입력받은 권한을 설정
        for (MemberRole r : input.getRoleList()) {
            if (r.getRole() != null) {
                r.setMember(member);
                list.add(r);
            }
        }
        member.setRoleList(list);
        log.info("member before save: {}", member.toString());
        memberRepository.save(member);
        DbResult rs = new DbResult("", 0);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
//    public ResponseEntity<?> patch(@ModelAttribute("member") Member input, @PathVariable("memberID") long memberID) {
//
//        log.info("input: {}", input.toString());
//
//        Member member = memberRepository.findById(memberID)
//                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
//        member.setName(input.getName());
//        member.setEmail(input.getEmail());
//        member.setEnabled(input.isEnabled());
//        // 기존 권한설정 삭제
//        List<MemberRole> list = member.getRoleList().stream().filter(role->{
//            return false; // Delete !
//        }).collect(Collectors.toList());
//
//        // 입력받은 권한을 설정
//        for (MemberRole r : input.getRoleList()) {
//            if (r.getRole() != null) {
//                r.setMember(member);
//                list.add(r);
//            }
//        }
//        member.setRoleList(list);
//        log.info("member: {}", member.toString());
//        memberRepository.save(member);
//
//        DbResult rs = new DbResult("", 0);
//        return new ResponseEntity<>(rs, HttpStatus.OK);
//    }


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
}



