package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Pet;
import com.korea.project2_team4.Model.Form.ProfileForm;
import com.korea.project2_team4.Service.ImageService;
import com.korea.project2_team4.Service.MemberService;
import com.korea.project2_team4.Service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.korea.project2_team4.Service.ProfileService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;


@Controller
//@RequiredArgsConstructor
@Builder
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final MemberService memberService;
    private final PetService petService;
    private final ImageService imageService;

    @GetMapping("/detail")
    public String profileDetail(Model model, Principal principal) {
        Member sitemember = this.memberService.getMember(principal.getName());


        model.addAttribute("profile",sitemember.getProfile());
        return "Profile/profile_detail";
    }

    @GetMapping("/update")
    public String profileupdate(Model model, ProfileForm profileForm,Principal principal) {
        Member sitemember = this.memberService.getMember(principal.getName());

        profileForm.setProfileName(sitemember.getProfile().getProfileName());
        profileForm.setContent(sitemember.getProfile().getContent());
        return "Profile/profile_form";
    }

    @PostMapping("/update")
    public String profileupdate(ProfileForm profileForm, BindingResult bindingResult, Principal principal) {
        Member sitemember = this.memberService.getMember(principal.getName());
        profileService.updateprofile(sitemember.getProfile(),profileForm.getProfileName(),profileForm.getContent());
        return "redirect:/profile/detail";
    }

    @GetMapping("/pet")
    public String pet() {
        return "Profile/pet_list";
    }


    @PostMapping("/addpet")
    public String addpet(@RequestParam("name") String name ,@RequestParam("content")String content , Principal principal ,
                         @RequestParam(value = "imageFile") MultipartFile imageFile) throws IOException, NoSuchAlgorithmException {
        Member sitemember = this.memberService.getMember(principal.getName());
        Pet pet = new Pet();
        pet.setName(name);
        pet.setContent(content);
        pet.setOwner(sitemember.getProfile());


//        if (imageFile != null && !imageFile.isEmpty()) {
//            imageService.uploadPostImage(imageFile, pet);
//        }
        petService.savePet(pet);
        profileService.setPetforprofile(sitemember.getProfile(),pet);

        return "redirect:/profile/pet";
    }



}
