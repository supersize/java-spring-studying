package com.studing.spring.controller;

import com.studing.spring.domain.Member;
import com.studing.spring.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * going to Member list page
     * @return
     */
    @GetMapping("/members")
    public String moveToMemberList(Model model) {
        List<Member> memberList = this.memberService.findMembers();

        model.addAttribute("memberList", memberList);

        return "member/memberListPage";
    }

    /**
     * going to register page.
     * @return
     */
    @GetMapping("/sign-up")
    public String moveToSignUpPage() {

        return "member/sign-up";
    }


    /**
     * request sign-up.
     * @param request
     * @param response
     * @param member
     * @return
     */
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
