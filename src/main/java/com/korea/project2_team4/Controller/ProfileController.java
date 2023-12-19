package com.korea.project2_team4.Controller;

import org.springframework.ui.Model;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.ProfileService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.List;



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

}
