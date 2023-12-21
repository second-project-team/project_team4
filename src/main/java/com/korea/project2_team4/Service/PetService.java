package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Pet;
import com.korea.project2_team4.Repository.PetRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public boolean deleteExistingFile(String existingFilePath){
        if (existingFilePath !=null && !existingFilePath.isEmpty()) {
            File existingFile = new File(existingFilePath);
            if (existingFile.exists()) {
                return existingFile.delete();
            }
        }
        return false;
    }

    public Pet getpetById(Long petid) {
        return this.petRepository.findById(petid).get();
    }
    public void savePet(Pet pet) {
        this.petRepository.save(pet);
    }

    public void deletePet(Pet pet) {
        String filepath = pet.getPetImage().getFilePath();
        if (filepath != null && !filepath.isEmpty()) {
            deleteExistingFile(filepath);
        }
        this.petRepository.delete(pet);
    }




}
