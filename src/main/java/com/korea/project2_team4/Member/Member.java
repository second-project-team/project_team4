package com.korea.project2_team4.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //아이디
    @Column(unique = true, columnDefinition = "TEXT")
    private String userId;

    //패스워드
    private String password;

    //이메일
    @Column(unique = true)
    private String email;

    //사용자 이름
    @Column(length = 30)
    private String userName;

    //사용자 닉네임
    @Column(length = 50)
    private String nickName;

    //사용자 핸드폰 번호
    private String phoneNum;



}
