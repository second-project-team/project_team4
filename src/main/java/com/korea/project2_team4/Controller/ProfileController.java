package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Form.ProfileForm;
import org.springframework.ui.Model;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Service.ProfileService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RequiredArgsConstructor
@Builder
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/detail")
    public String profileDetail(Model model) {
        Profile testProfile = profileService.getProfilelist().get(0);
//        System.out.println(testProfile.getProfileName());

        model.addAttribute("profile",testProfile);
        return "profile_detail";
    }

    @GetMapping("/update")
    public String profileupdate(Model model, ProfileForm profileForm) {
        Profile testProfile = profileService.getProfilelist().get(0);

        profileForm.setProfileName(testProfile.getProfileName());
        profileForm.setContent(testProfile.getContent());
        return "profile_form";
    }

    @PostMapping("/update")
    public String profileupdate(ProfileForm profileForm, BindingResult bindingResult) {
        Profile testProfile = profileService.getProfilelist().get(0);
        profileService.updateprofile(testProfile,profileForm.getProfileName(),profileForm.getContent());
        return "redirect:/profile/detail";
    }



}
