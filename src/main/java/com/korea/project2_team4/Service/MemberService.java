package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Form.MemberCreateForm;
import com.korea.project2_team4.Repository.MemberRepository;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Builder
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    // 멤버 생성
    public Member create(MemberCreateForm memberCreateForm) {
        Member member = new Member();

        member.setUserName(memberCreateForm.getUserName());
        member.setPassword(passwordEncoder.encode(memberCreateForm.getPassword()));

        member.setEmail(memberCreateForm.getEmail());

        member.setRealName(memberCreateForm.getRealName());
        member.setNickName(memberCreateForm.getNickName());
        member.setPhoneNum(memberCreateForm.getPhoneNum());

        memberRepository.save(member);

        return member;
    }

    public Optional<Member> getMember(String username) {
        return memberRepository.findByUserName(username);
    }

}
