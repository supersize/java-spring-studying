package com.studing.spring.controller;

import com.studing.spring.domain.Member;
import com.studing.spring.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/sign-up")
    public String moveToSignUpPage() {

        return "member/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp (
            HttpServletRequest request
            , HttpServletResponse response
            , Member member) {
        try {
            Member newMember = memberService.findOne(memberService.join(member)).get();

            return "redirect:/";
        } catch (Exception e) {
//            throw new RuntimeException(e);
            String errorMessage = e.getMessage();
        }

        return "";
    }
}
