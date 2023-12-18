package com.korea.project2_team4.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profile owner;

    private String species;
    private Long age;


    @OneToOne(mappedBy = "petImage", cascade = CascadeType.REMOVE)
    private Image petImage;

}
