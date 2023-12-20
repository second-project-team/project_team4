package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Form.PostForm;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Builder
@RequestMapping("/post")
public class PostController {


    @GetMapping("/main")
    public String main() {

        return "community_main";
    }

    @GetMapping("/createPost")
    public String createPost(Model model, PostForm postForm) {
        return "createPost_form";
    }


    @PostMapping("/createPost")
    public String profileupdate(PostForm postForm, BindingResult bindingResult) {
//        Profile testProfile = profileService.getProfilelist().get(0);
//        profileService.updateprofile(testProfile,profileForm.getProfileName(),profileForm.getContent());
        return "redirect:/community/main";
    }

}
