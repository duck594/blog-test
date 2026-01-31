package com.backend.domain.member.controller;


import com.backend.domain.member.entity.Member;
import com.backend.domain.member.join.MemberJoin;
import com.backend.domain.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String bjoin() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(MemberJoin memberJoin, HttpServletRequest req) {
        String username = memberJoin.getUsername();
        String password = memberJoin.getPassword();

        Member joinform = memberService.getMemberByUsername(username);

        if(joinform != null) {
            return "redirect:/?errorMsg=Already exists username";
        }

        Member member = memberService.join(memberJoin);

        try {
            req.login(username, password);
            System.out.println("로그인 완료");
        }   catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/member/profile";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String Profile(Principal principal, Model model) {
        Member member = memberService.getMemberByUsername(principal.getName());

        model.addAttribute("member", member);

        return "member/profile";
    }

}
