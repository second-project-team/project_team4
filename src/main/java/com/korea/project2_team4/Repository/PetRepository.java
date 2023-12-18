package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
