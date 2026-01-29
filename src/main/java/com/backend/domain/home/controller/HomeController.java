package com.backend.domain.home.controller;

import com.backend.domain.member.entity.Member;
import com.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private final MemberService memberService;

  @RequestMapping("/")
  public String home(Principal principal, Model model) {
    Member loginedMember = null;
    String loginedMemberProfileImgUrl = null;

    if(principal != null && principal.getName() != null) {
      loginedMember = memberService.getMemberByUsername(principal.getName()).orElse(null);
    }

    if(loginedMember != null) {
      loginedMemberProfileImgUrl = "/gen/" + loginedMember.getProfileImg();
    }

    model.addAttribute("loginedMember", loginedMember);
    model.addAttribute("loginedMemberProfileImgUrl", loginedMemberProfileImgUrl);

    return "home/index"; // Changed from "home" to "home/index"
  }
}