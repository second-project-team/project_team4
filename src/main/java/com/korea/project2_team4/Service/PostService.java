package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Repository.PostRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post){
        postRepository.save(post);
    }

    public List<Post> postList(){
        return postRepository.findAll();
    }
}
