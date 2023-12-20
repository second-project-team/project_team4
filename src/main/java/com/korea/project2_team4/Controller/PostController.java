package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Form.PostForm;
import com.korea.project2_team4.Service.ImageService;
import com.korea.project2_team4.Service.MemberService;
import com.korea.project2_team4.Service.PostService;
import lombok.Builder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Builder
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    private final ImageService imageService;
    private final MemberService memberService;


    @GetMapping("/main")
    public String main() {

        return "community_main";
    }

    @GetMapping("/createPost")
    public String createPost(Model model, PostForm postForm) {
        return "createPost_form";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createPost")
    public String createPost(Principal principal, PostForm postForm, BindingResult bindingResult, @RequestParam(value = "imageFiles") List<MultipartFile> imageFiles) throws IOException, NoSuchAlgorithmException {//      Profile testProfile = profileService.getProfilelist().get(0);
//      profileService.updateprofile(testProfile,profileForm.getProfileName(),profileForm.getContent());
        Post post = new Post();
//        System.out.println(imageFiles.size());
        Member sitemember = this.memberService.getMember(principal.getName());
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setCreateDate(LocalDateTime.now());
        post.setAuthor(sitemember.getProfile());
        if (imageFiles != null && !imageFiles.isEmpty()) {
            imageService.uploadPostImage(imageFiles, post);
        }
        postService.save(post);

        return "redirect:/post/community/main";
    }

    @GetMapping("/community/main")
    public String communityMain(Model model) {
        List<Post> allPosts = postService.postList();
        model.addAttribute("allPosts", allPosts);
        return "community_main";
    }



    @GetMapping("/search")
    public String searchPosts(@RequestParam(value = "kw", defaultValue = "") String kw, @RequestParam(name = "sort",required = false) String sort, Model model) {
        List<Post> searchResults  = postService.searchPosts(kw);

        System.out.println(searchResults.size());

        model.addAttribute("searchResults",searchResults);
        model.addAttribute("kw", kw);
        model.addAttribute("sort", sort);

        return "search_form";
    }


}
