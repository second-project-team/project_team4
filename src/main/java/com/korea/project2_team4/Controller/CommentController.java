package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Service.CommentService;
import com.korea.project2_team4.Service.MemberService;
import com.korea.project2_team4.Service.PostService;
import com.korea.project2_team4.Service.ProfileService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
@Builder
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final ProfileService profileService;
    private final MemberService memberService;


    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id,
                                @RequestParam(value = "content") String content, Principal principal) {
        Post post = this.postService.getPost(id);
        Member member = this.memberService.getMember(principal.getName());
        commentService.create(post, content, member.getProfile());

        return "redirect:/post/detail/{id}";
    }
}
