package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.CommentLike;
import com.korea.project2_team4.Model.Entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
