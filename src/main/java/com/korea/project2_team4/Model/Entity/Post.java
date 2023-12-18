package com.korea.project2_team4.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profile author;

    private String title;
    private String content;

    @OneToMany(mappedBy = "postImages")
    private List<Image> postImages;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    private LocalDateTime modifyDate;




}
