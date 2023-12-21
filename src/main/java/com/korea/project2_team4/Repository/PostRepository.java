package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%',:kw,'%'))" +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%',:kw,'%'))")
    List<Post> findAllByKw(@Param("kw") String kw);

    Page<Post> findAll(Pageable pageable);
}
