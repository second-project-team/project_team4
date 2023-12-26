package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Entity.*;
import com.korea.project2_team4.Model.Form.PostForm;
import com.korea.project2_team4.Repository.PostRepository;
import com.korea.project2_team4.Service.*;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Builder
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final ImageService imageService;
    private final MemberService memberService;
    private final TagService tagService;
    private final TagMapService tagMapService;


    @GetMapping("/main")
    public String main() {

        return "community_main";
    }

    @GetMapping("/createPost")
    public String createPost(Model model, PostForm postForm) {
        List<Tag> allTags = tagService.getAllTags();
        model.addAttribute("allTags", allTags);
        return "createPost_form";
    }

    //테스트 데이터
    @GetMapping("/TestPost")
    public String saveTestPost() {
        postService.saveTestPost();

        return "redirect:/";

    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createPost")
    public String createPost(Principal principal, PostForm postForm, BindingResult bindingResult
            , @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles, @RequestParam(value = "selectedTagNames", required = false) List<String> selectedTagNames) throws IOException, NoSuchAlgorithmException {
        //      Profile testProfile = profileService.getProfilelist().get(0);
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
        if (selectedTagNames != null && !selectedTagNames.isEmpty()) {
            for (String selectedTagName : selectedTagNames) {
                Tag tag = tagService.getTagByTagName(selectedTagName);
                TagMap tagMap = new TagMap();
                tagMap.setPost(post);
                tagMap.setTag(tag);
                tagMapService.save(tagMap);
            }
        }
        return "redirect:/post/community/main";
    }

    @GetMapping("/community/main")
    public String communityMain(Model model, @RequestParam(name = "sort", required = false) String sort, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(name = "searchTagName", required = false) String searchTagName) {
        Page<Post> allPosts;
        allPosts = postService.postList(page);
        if (searchTagName == null) {
            searchTagName = "";  // 기본적으로 빈 문자열로 설정
        }
        if (sort != null && !sort.isEmpty()) {
            if (sort.equals("latest")) {
                allPosts = postService.postList(page);
            } else if (sort.equals("likeCount")) {
                allPosts = postService.getPostsOrderByLikeCount(page);
            } else {
                allPosts = postService.getPostsOrderByCommentCount(page);
            }
        }
        if (searchTagName != null && !searchTagName.isEmpty()) {
            allPosts = postService.getPostsByTagName(page, searchTagName);
        }
        model.addAttribute("searchTagName", searchTagName);
        model.addAttribute("sort", sort);
        model.addAttribute("paging", allPosts);
        return "community_main";
    }
    // 내일 전체로 다시 돌아가는 버튼 만들기.


    @GetMapping("/search")
    public String searchPosts(@RequestParam(value = "kw", defaultValue = "") String kw, @RequestParam(name = "sort", required = false) String sort, Model model) {
        List<Post> searchResults = postService.searchPosts(kw);

        System.out.println(searchResults.size());

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("kw", kw);
        model.addAttribute("sort", sort);

        return "search_form";
    }

    @GetMapping("/detail/{id}/{hit}")
    public String postDetail(Model model, @PathVariable("id") Long id, @PathVariable("hit") Integer hit) {
        if (hit == 0) {
            Post post = postService.getPostIncrementView(id);
            model.addAttribute("post", post);
        } else {
            Post post = postService.getPost(id);
            model.addAttribute("post", post);
        }

        return "postDetail_form";
    }

    @PostMapping("/postLike")
    public String postLike(Principal principal, @RequestParam("id") Long id) {
        if (principal != null) {
            Post post = this.postService.getPost(id);
            Member member = this.memberService.getMember(principal.getName());
            Long postId = post.getId();
            if (postService.isLiked(post, member)) {
                postService.unLike(post, member);
            } else {
                postService.Like(post, member);
            }
            return "redirect:/post/detail/" + postId + "/1";
        } else {
            return "redirect:/member/login";
        }
    }

    @PostMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id) {

        postService.deleteById(id);

        return "redirect:/post/community/main";
    }

    @PostMapping("/updatePost/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatePost) {

        Post existingPost = postRepository.findById(id).orElse(null);

        if (existingPost != null) {

            existingPost.setTitle(updatePost.getTitle());
            existingPost.setContent(updatePost.getContent());
            existingPost.setModifyDate(LocalDateTime.now());

            postRepository.save(existingPost);
        }

        return "redirect:/post/detail/{id}/1";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myPosts")
    public String getMyPosts(Model model,Principal principal,@RequestParam(value = "page", defaultValue = "0") int page){
        Profile author = memberService.getMember(principal.getName()).getProfile();
        Page<Post> myPosts = postService.getMyPosts(page,author);
        model.addAttribute("paging", myPosts);
        return "Member/findMyPosts_form";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myLikedPosts")
    public String getMyLikedPosts(Model model,Principal principal,@RequestParam(value = "page", defaultValue = "0") int page){
        Member member = memberService.getMember(principal.getName());
        Page<Post> myLikedPosts = postService.getMyLikedPosts(page,member);
        model.addAttribute("paging", myLikedPosts);
        return "Member/findMyLikedPosts_form";
    }


}
