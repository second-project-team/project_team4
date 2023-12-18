package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.CommentLike;
import com.korea.project2_team4.Model.Entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
}
