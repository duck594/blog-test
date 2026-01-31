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

    return "home/index"; // Changed from "home" to "home/index"
  }
}