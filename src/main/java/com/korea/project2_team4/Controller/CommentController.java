package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Comment;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Repository.CommentRepository;
import com.korea.project2_team4.Service.CommentService;
import com.korea.project2_team4.Service.MemberService;
import com.korea.project2_team4.Service.PostService;
import com.korea.project2_team4.Service.ProfileService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/comment")
@Builder
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final ProfileService profileService;
    private final MemberService memberService;
    private final CommentRepository commentRepository;


    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id,
                                @RequestParam(value = "content") String content, Principal principal) {
        Post post = this.postService.getPost(id);
        Member member = this.memberService.getMember(principal.getName());
        commentService.create(post, content, member.getProfile());

        return "redirect:/post/detail/" + id + "/1";
    }

    @PostMapping("/commentLike")
    public String commentLike(Principal principal, @RequestParam("id") Long id) {


        if (principal != null) {
            Comment comment = this.commentService.getComment(id);
            Long postId = comment.getPost().getId();
            Member member = this.memberService.getMember(principal.getName());

            if (commentService.isLiked(comment, member)) {
                commentService.unLike(comment, member);

            } else {
                commentService.Like(comment, member);
            }

            return "redirect:/post/detail/" + postId + "/1";

        } else {
            return "redirect:/member/login";
        }

    }

    @PostMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id) {
        Comment comment = commentService.getComment(id);
        Long postId = comment.getPost().getId();
        commentService.deleteById(id);


        return "redirect:/post/detail/" + postId + "/1";
    }

    @PostMapping("/updateComment/{id}")
    public String updateComment(Model model,@PathVariable Long id, @ModelAttribute Comment updateComment) {

        Comment existingComment = commentRepository.findById(id).orElse(null);

        Comment comment = commentService.getComment(id);
        Long postId = comment.getPost().getId();

        if (existingComment != null) {

            existingComment.setContent(updateComment.getContent());
            existingComment.setModifyDate(LocalDateTime.now());

            commentService.save(existingComment);

            Post post = existingComment.getPost();
            model.addAttribute("post", post);

        }

        return "redirect:/post/detail/" + postId + "/1";

    }




}
