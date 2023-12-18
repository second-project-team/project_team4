package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
