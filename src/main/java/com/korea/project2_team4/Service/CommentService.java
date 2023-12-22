package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Comment;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Repository.CommentRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    //댓글 좋아요 기능
    public void Like(Comment comment, Member member) {
        comment.getLikeMembers().add(member);
        this.commentRepository.save(comment);
    }

    public void unLike(Comment comment, Member member) {
        comment.getLikeMembers().remove(member);
        this.commentRepository.save(comment);
    }

    public boolean isLiked(Comment comment, Member member) {
        if (comment == null) {
            return false;
        }
        return comment.getLikeMembers().contains(member);
    }

    public Comment getComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.orElse(null); //값이 없으면 null 값으로 반환
    }

    public void deleteById(Long id) {
      commentRepository.deleteById(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
