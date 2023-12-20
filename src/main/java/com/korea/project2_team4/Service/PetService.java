package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Pet;
import com.korea.project2_team4.Repository.PetRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    public void savePet(Pet pet) {
        this.petRepository.save(pet);
    }

}
