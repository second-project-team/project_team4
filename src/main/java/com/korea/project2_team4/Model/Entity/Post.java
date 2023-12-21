package com.korea.project2_team4.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profile  author;

    private String title;

    private String content;


    @OneToMany(mappedBy = "postImages", cascade = CascadeType.REMOVE )
    private List<Image> postImages;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE )
    private List<Comment> comments;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToMany
    private Set<Member> likeMembers;






}
