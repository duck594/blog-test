package com.backend.domain.member.controller;


import com.backend.domain.member.entity.Member;
import com.backend.domain.member.join.MemberJoin;
import com.backend.domain.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    @PostMapping("/join")
    public Object join(MemberJoin memberJoin, HttpSession session) {
        String username = memberJoin.getUsername();

        Member joinform = memberService.getMemberByUsername(username);

        if(joinform != null) {
            return "redirect:/?errorMsg=Already exists username";
        }

        Member member = memberService.join(memberJoin);

        return member;
    }
}
