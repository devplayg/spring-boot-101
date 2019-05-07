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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

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

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Member member, Member input) throws RollbackException {
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
    }

//    @Transactional( rollbackOn = RollbackException.class)
//    public void save(ExObj user, ExObj2 userDetails) throws RollBackException {
//
//        ExObj resUser = rollBackRepository.save(user);
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


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @PatchMapping("{memberID}")
    public ResponseEntity<?> patch(@ModelAttribute("member") Member input, @PathVariable("memberID") long memberID) {
        log.info("input: {}", input.toString());

        Member member = memberRepository.findById(memberID)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));
        member.setName(input.getName());
        member.setEmail(input.getEmail());
        member.setEnabled(input.isEnabled());
//        member.setRoleList(null);

//        memberRepository.deleteRoleListByMemberID(memberID);
        memberRoleRepository.deleteInBatch(member.getRoleList());




//        for (MemberRole r : member.getRoleList()) {
//            member.getRoleList().remove(r);
//        }
        List<MemberRole> list = member.getRoleList().stream().filter(str->{
            return false; // Delete !
        }).collect(Collectors.toList());
//        member.setRoleList(list);
        for (MemberRole r : input.getRoleList()) {
            if (r.getRole() != null) {
                r.setMember(member);
                member.getRoleList().add(r);
            }
        }
        log.info("member: {}", member.toString());
        memberRepository.save(member);


        // 사용자 정보 조회
//        Member member = memberRepository.findById(memberID)
//                .orElseThrow(() -> new ResourceNotFoundException("member", "id", memberID));

//        this.update(member, input);
//        if (input.getRoleList().size() > 0) {
//            this.updateMemberRole(member, input);
//        }

//        // 사용자 정보 업데이트
//        member.setName(input.getName());
//        member.setEmail(input.getEmail());
//        member.setEnabled(input.isEnabled());
//        memberRepository.save(member);
//
//
//        // 사용자 권한정보 삭제
//        memberRoleRepository.deleteAll(member.getRoleList());
//        List<MemberRole> list = new ArrayList<>();
//        for (MemberRole r : input.getRoleList()) {
//            if (r.getRole() != null) {
//                r.setMember(member);
//                list.add(r);
//            }
////                throw  new NullPointerException();
//        }
//
//        if (list.size() > 0) {
//            memberRoleRepository.saveAll(list);
//        }

        DbResult rs = new DbResult("", 0);
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



