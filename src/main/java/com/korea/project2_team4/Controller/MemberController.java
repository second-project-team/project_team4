package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Form.MemberCreateForm;
import com.korea.project2_team4.Service.MemberService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
@RequestMapping("/member")
@Builder
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {

        return "Member/signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {



        memberService.create(memberCreateForm);



        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {

        return "Member/login_form";
    }
    @PostMapping("/login")
    public String login(String username, String password){
        return "redirect:/";
    }



}
