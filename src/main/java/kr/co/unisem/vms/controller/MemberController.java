package kr.co.unisem.vms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

    // 로그인
    @GetMapping("display")
    public String display() {
        return "member/member";
    }
}
