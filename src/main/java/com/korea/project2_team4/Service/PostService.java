package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Builder
@Service
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }

    public List<Post> postList() {
        return postRepository.findAll();
    }


    // 게시물 검색기능
    public List<Post> searchPosts(String kw) {
        return postRepository.findAllByKw(kw);
    }
}
