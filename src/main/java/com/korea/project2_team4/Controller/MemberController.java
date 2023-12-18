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
        if (bindingResult.hasErrors()) {
            return "Member/signup_form";
        }

        if (!memberCreateForm.getPassword().equals(memberCreateForm.getRe_password())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "/Member/signup_form";
        }

        try {
            memberService.create(memberCreateForm);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
            return "/Member/signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/Member/signup_form";
        }

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
