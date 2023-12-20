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

    @PostConstruct
    public void init() {
        saveTestPost();
    }

    @Transactional
    public void saveTestPost() {
        if (postRepository.findAll().isEmpty()) {
            for (int i = 1; i <= 10; i++) {

                Post post = new Post();

                post.setTitle(String.format("테스트 데이터 제목 입니다:[%03d].", i));
                post.setContent("테스트 데이터 제목 입니다.");
                postRepository.save(post);
            }

        }
    }

    // 게시물 검색기능
    public List<Post> searchPosts(String kw) {
        return postRepository.findAllByKw(kw);
    }
}
