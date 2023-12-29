package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Tag;
import com.korea.project2_team4.Repository.TagRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Builder
@Service
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagByTagName(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseThrow(() -> new RuntimeException("Tag not found for name: " + tagName));
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }
    public boolean tagExists(String tagName) {
        // 태그 이름을 사용하여 데이터베이스에서 태그를 조회하고 존재 여부를 반환
        return tagRepository.existsByName(tagName);

    }
}
