package com.korea.project2_team4.Model.Entity;

import com.korea.project2_team4.Repository.ProfileRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "profile")
    private Member member;

    private Image profileImage;

    private String profileName;
    private String content;
    private List<Profile> following;
    private List<Profile> followers;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    private LocalDateTime modifyDate;
}
