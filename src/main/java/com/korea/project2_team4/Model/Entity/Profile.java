package com.korea.project2_team4.Model.Entity;

import com.korea.project2_team4.Repository.ProfileRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member member;

    @OneToOne(mappedBy = "profileImage")
    private Image profileImage;

    private String profileName;
    private String content;

    @ManyToMany(mappedBy = "followers")
    private List<Profile> following = new ArrayList<>();

    @ManyToMany
    private List<Profile> followers = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE )
    private List<Pet> pets;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE )
    private List<Post> posts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE )
    private List<Comment> comments;

    private LocalDateTime modifyDate;
}
