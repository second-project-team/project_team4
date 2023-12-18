package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entitiy.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
