package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Pet;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public List<Profile> getProfilelist() {
        return this.profileRepository.findAll();
    }

    public void updateprofile(Profile profile, String profilename, String content) {
        profile.setProfileName(profilename);
        profile.setContent(content);
        this.profileRepository.save(profile);
    }

    public Profile setDefaultProfile(Member member) {
        Profile profile = new Profile();
        profile.setMember(member);
        profile.setProfileName(member.getUserName());
        profile.setContent(" ");
        return this.profileRepository.save(profile);

    }

    public void setPetforprofile(Profile profile,Pet pet) {
        profile.getPetList().add(pet);
        this.profileRepository.save(profile);
    }


}
