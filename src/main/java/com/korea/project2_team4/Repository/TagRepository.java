package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
}
