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
    @Query("SELECT p FROM Post p " + "LEFT JOIN p.author pr " + "LEFT JOIN p.comments c " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%',:kw,'%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%',:kw,'%')) " +
            "OR LOWER(pr.profileName) LIKE LOWER(CONCAT('%',:kw,'%')) " +
            "OR LOWER(c.content) LIKE LOWER(CONCAT('%',:kw,'%')) " +
            "ORDER BY p.id DESC")
    List<Post> findAllByKw(@Param("kw") String kw);

    Page<Post> findAll(Pageable pageable);

    // 태그명을 기준으로 해당 태그를 갖고 있는 포스트를 페이징하여 조회
    @Query("SELECT p FROM Post p JOIN p.tagMaps tm JOIN tm.tag t WHERE t.name = :tagName")
    Page<Post> findByTagName(@Param("tagName") String tagName, Pageable pageable);

    @Query("SELECT p FROM Post p ORDER BY SIZE(p.comments) DESC")
    Page<Post> findAllOrderByCommentsSizeDesc(Pageable pageable);
    @Query("SELECT p FROM Post p ORDER BY SIZE(p.likeMembers) DESC")
    Page<Post> findAllOrderByLikeMembersSizeDesc(Pageable pageable);


    //작성자 게시물 리스트all 반환
    @Query("SELECT p FROM Post p WHERE p.author.profileName LIKE %:name%")
    List<Post> findAllByauthor(@Param("name")String name);
    //작성자 게시물 리스트 페이징처리 반환
    @Query("SELECT p FROM Post p WHERE p.author.profileName LIKE %:name%")
    Page<Post> findAllByauthorPage(@Param("name")String name, Pageable pageable);

}
