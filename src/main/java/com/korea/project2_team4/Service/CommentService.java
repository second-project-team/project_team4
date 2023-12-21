package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Comment;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Repository.CommentRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class CommentService {
    private CommentRepository commentRepository;

    public void create(Post post, String content, Profile author) {
        Comment comment = new Comment();

        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setPost(post);
        comment.setAuthor(author);

        this.commentRepository.save(comment);

    }
}
