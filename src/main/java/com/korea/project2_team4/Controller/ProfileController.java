package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Form.ProfileForm;
import com.korea.project2_team4.Service.MemberService;
import org.springframework.ui.Model;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Service.ProfileService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
//@RequiredArgsConstructor
@Builder
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final MemberService memberService;
    @GetMapping("/detail")
    public String profileDetail(Model model, Principal principal) {
        Member sitemember = this.memberService.getMember(principal.getName());


        model.addAttribute("profile",sitemember.getProfile());
        return "profile_detail";
    }

    @GetMapping("/update")
    public String profileupdate(Model model, ProfileForm profileForm,Principal principal) {
        Member sitemember = this.memberService.getMember(principal.getName());

        profileForm.setProfileName(sitemember.getProfile().getProfileName());
        profileForm.setContent(sitemember.getProfile().getContent());
        return "profile_form";
    }

    @PostMapping("/update")
    public String profileupdate(ProfileForm profileForm, BindingResult bindingResult, Principal principal) {
        Member sitemember = this.memberService.getMember(principal.getName());

        profileService.updateprofile(sitemember.getProfile(),profileForm.getProfileName(),profileForm.getContent());
        return "redirect:/profile/detail";
    }



}
