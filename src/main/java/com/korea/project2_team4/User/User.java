package com.korea.project2_team4.User;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User { //자세한 애너테이션 추가는 추후 수정 예정 ---> 테스트 해볼때, 막힘 없기 위해
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userId;
    private String password;

    private String nickname;
    private String role;
    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private String fileName;  // 파일 원본명

    private String filePath;  // 파일 저장 경로

    private String provider;

    private String phone;

}