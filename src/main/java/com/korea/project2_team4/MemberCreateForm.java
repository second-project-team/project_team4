package com.korea.project2_team4;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

    @Size(min = 4, message = "아이디는 4자 이상 입력해주세요.")
    @NotEmpty(message = "아이디는 필수 항목 입니다.")
    private String userId;

    @Size(min = 8, message = "비밀번호는 8자 이상 입력해주세요.")
    @NotEmpty(message = "비밀번호는 필수 항목 입니다.")
    private String password;

    @Size(min=8, message = "비밀번호는 8자 이상 입력해 주세요.")
    @NotEmpty(message = "비밀번호 확인은 필수 항목 입니다.")
    private String confirmPassword;

    @Email
    @NotEmpty(message = "이메일은 필수 항목 입니다.")
    private String email;

    @NotEmpty(message = "유저 실명은 필수 항목 입니다.")
    private String userName;

    @NotEmpty(message = "유저 닉네임은 필수 항목 입니다..")
    private String nickName;

    @NotEmpty(message = "핸드폰 번호는 필수 항목 입니다.")
    private String phoneNum;

}
