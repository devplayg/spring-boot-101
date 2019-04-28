package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.filter.MemberFilter;
import kr.co.unisem.vms.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // 로그인
    @GetMapping
    public String index(@ModelAttribute MemberFilter filter, Model model) {
        return "member/member";
    }

    // 로그인
    @GetMapping("/members")
    public ResponseEntity<Object> list(@ModelAttribute("filter") MemberFilter filter) {
//        memberRepository.fin
//        List<Member> list = memberRepository.getArticlesPaged(paging);
//        log.info(paging.toString());
//
//        if (!paging.isFastPaging()) {
//            int total = commonRepository.selectTotalRows();
//            DbResult rs = new DbResult("", list, total);
//            return new ResponseEntity<>(rs, HttpStatus.OK);
//        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
