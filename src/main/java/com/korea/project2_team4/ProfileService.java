package com.korea.project2_team4;

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


}
