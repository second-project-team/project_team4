package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.CommentLike;
import com.korea.project2_team4.Model.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
