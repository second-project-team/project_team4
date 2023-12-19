package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Config.OAuth2.OAuth2UserInfo;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Form.MemberCreateForm;
import com.korea.project2_team4.Service.FollowService;
import com.korea.project2_team4.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.boot.Banner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/member")
@Builder
public class MemberController {

    private final MemberService memberService;
    private final FollowService followService;


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
            return "Member/signup_form";
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
            return "Member/signup_form";
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

    @GetMapping("/signup/social")
    public String signup(MemberCreateForm memberCreateForm,
                         HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        OAuth2UserInfo socialLogin = (OAuth2UserInfo) session.getAttribute("SOCIAL_LOGIN");

        if (socialLogin != null) {
            memberCreateForm.setNickName(socialLogin.getName());
            memberCreateForm.setUserName(socialLogin.getProvider() + "_" + socialLogin.getProviderId());
            memberCreateForm.setPassword(socialLogin.getProvider() + "_" + socialLogin.getProviderId());
            memberCreateForm.setRe_password(socialLogin.getProvider() + "_" + socialLogin.getProviderId());
            memberCreateForm.setRealName(socialLogin.getName());
            memberCreateForm.setEmail(socialLogin.getEmail());
            memberCreateForm.setProvider(socialLogin.getProvider());
            memberCreateForm.setProviderID(socialLogin.getProviderId());
//            memberCreateForm.setSnsImage(socialLogin.getImage());
        }
        model.addAttribute("socialLogin", socialLogin);
        return "Member/social_signup_form";
    }

    @PostMapping("/signup/social")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult,
//                         @RequestParam(value = "profile-picture", defaultValue = "") String profile,
                         HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        OAuth2UserInfo socialLogin = (OAuth2UserInfo) session.getAttribute("SOCIAL_LOGIN");

        if (socialLogin != null) {
            memberCreateForm.setNickName(socialLogin.getName());
            memberCreateForm.setUserName(socialLogin.getProvider() + "_" + socialLogin.getProviderId());
            memberCreateForm.setPassword(socialLogin.getProvider() + "_" + socialLogin.getProviderId());
            memberCreateForm.setRe_password(socialLogin.getProvider() + "_" + socialLogin.getProviderId());
            memberCreateForm.setRealName(socialLogin.getName());
            memberCreateForm.setEmail(socialLogin.getEmail());
            memberCreateForm.setProvider(socialLogin.getProvider());
            memberCreateForm.setProviderID(socialLogin.getProviderId());
//            memberCreateForm.setSnsImage(socialLogin.getImage());
        }
        model.addAttribute("socialLogin", socialLogin);

        if (bindingResult.hasErrors()) {
            return "Member/social_signup_form";
        }

        if (!memberCreateForm.getPassword().equals(memberCreateForm.getRe_password())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "Member/social_signup_form";
        }

        try {

//            소셜 로그인 프로필 이미지
//
//            if (profile.equals("sns-picture")) {
//                memberCreateForm.setImageType(0);
//            } else {
//                memberCreateForm.setImageType(1);
//            }

            Member member = memberService.create(memberCreateForm);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
            return "/Member/social_signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "Member/social_signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/member")
    public String saveDefaultAdmin() {
        memberService.saveDefaultAdmin();

        return "redirect:/restaurant/main";

    }

    @GetMapping("/member1")
    public String saveDefaultUser() {
        memberService.saveDefaultUser();

        return "redirect:/restaurant/main";

    }




}
