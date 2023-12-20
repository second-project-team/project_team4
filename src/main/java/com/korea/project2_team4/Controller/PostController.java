package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Model.Form.PostForm;
import com.korea.project2_team4.Service.ImageService;
import com.korea.project2_team4.Service.PostService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Builder
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    private final ImageService imageService;


    @GetMapping("/main")
    public String main() {

        return "community_main";
    }

    @GetMapping("/createPost")
    public String createPost(Model model, PostForm postForm) {
        return "createPost_form";
    }


    @PostMapping("/createPost")
    public String createPost(PostForm postForm, BindingResult bindingResult,List<MultipartFile> imageFiles) throws IOException, NoSuchAlgorithmException {
//      Profile testProfile = profileService.getProfilelist().get(0);
//      profileService.updateprofile(testProfile,profileForm.getProfileName(),profileForm.getContent());
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setCreateDate(LocalDateTime.now());
        imageService.uploadPostImage(imageFiles,post);
        postService.save(post);

        return "redirect:/community/main";
    }
    @GetMapping("/community/main")
    public String communityMain(Model model){
        List<Post> allPosts = postService.postList();
        model.addAttribute("allPosts",allPosts);
        return "community_main";
    }

}
