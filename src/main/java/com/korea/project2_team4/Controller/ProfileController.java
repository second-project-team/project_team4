package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Pet;
import com.korea.project2_team4.Model.Entity.Profile;
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
    public String profileupdate(ProfileForm profileForm, @RequestParam(value = "profileImage") MultipartFile newprofileImage,
                                BindingResult bindingResult, Principal principal)throws Exception {
        Member sitemember = this.memberService.getMember(principal.getName());

        if (profileForm.getProfileImage() !=null && !profileForm.getProfileImage().isEmpty()) {
            imageService.saveImgsForProfile(sitemember.getProfile(), newprofileImage); // 기존 이미지 먼저 지우게 된다.
        }

        profileService.updateprofile(sitemember.getProfile(),profileForm.getProfileName(),profileForm.getContent());



        return "redirect:/profile/detail";
    }

    @PostMapping("/deleteProfileImage")
    public String deleteProfileImage(@RequestParam("profileid")Long profileid) {
        Profile profile = profileService.getProfileById(profileid);
        imageService.deleteProfileImage(profile);

        return "redirect:/profile/detail";
    }


    @GetMapping("/pet")
    public String pet() {

        return "Profile/pet_list";
    }


    @PostMapping("/addpet")
    public String addpet(@RequestParam("name") String name ,@RequestParam("content")String content , Principal principal ,
                         MultipartFile imageFile) throws Exception, NoSuchAlgorithmException {
        Member sitemember = this.memberService.getMember(principal.getName());
        Pet pet = new Pet();
        pet.setName(name);
        pet.setContent(content);
        pet.setOwner(sitemember.getProfile());
        petService.savePet(pet);

        if (imageFile != null && !imageFile.isEmpty() && pet !=null) {
            imageService.saveImgsForPet(pet,imageFile);
        }

        profileService.setPetforprofile(sitemember.getProfile(),pet);

        return "redirect:/profile/pet";
    }

    @PostMapping("/deletepet")
    public String deletepet(@RequestParam("petid")Long petid) {
        Pet pet = petService.getpetById(petid);
        petService.deletePet(pet);
        return "redirect:/profile/detail";
    }





}
