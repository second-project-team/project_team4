package com.korea.project2_team4.Service;

import com.korea.project2_team4.Config.UserRole;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Model.Form.MemberCreateForm;
import com.korea.project2_team4.Repository.MemberRepository;
import com.korea.project2_team4.Repository.ProfileRepository;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Builder
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;


    // 멤버 생성
    public Member create(MemberCreateForm memberCreateForm) {
        Member member = new Member();

        member.setUserName(memberCreateForm.getUserName());
        member.setPassword(passwordEncoder.encode(memberCreateForm.getPassword()));

        member.setEmail(memberCreateForm.getEmail());
        member.setCreateDate(LocalDateTime.now());
        member.setRole(UserRole.USER.getValue());

        member.setRealName(memberCreateForm.getRealName());
        member.setNickName(memberCreateForm.getNickName());
        member.setPhoneNum(memberCreateForm.getPhoneNum());

        memberRepository.save(member);
        member.setProfile(profileService.setDefaultProfile(member));
        return member;
    }

    public Member getMember(String username) {
        return this.memberRepository.findByUserName(username).orElse(null);
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 멤버의 아이디를 찾을 수 없습니다: " + memberId));
    }

    @PostConstruct
    public void init() {
        saveDefaultAdmin();
        saveDefaultUser();
    }

    @Transactional
    public void saveDefaultAdmin() { // 테스트 관리자 아이디 아이디: admin, 비밀 번호 admin123!
        if (memberRepository.findByUserName("admin").isEmpty()) {
            Member member = new Member();
            member.setUserName("admin");
            member.setPassword(passwordEncoder.encode("admin123!"));
            member.setEmail("admin@gmail.com");
            member.setPhoneNum("000-0000-0000");
            member.setRole("ROLE_ADMIN");
            member.setRealName("관리자");
            member.setNickName("관리자");
            member.setCreateDate(LocalDateTime.now());


            memberRepository.save(member);

            Profile adminProfile = profileRepository.findByProfileName("관리자")
                    .orElseGet(() -> {
                        Profile newProfile = new Profile();
                        newProfile.setProfileName("관리자");
                        return newProfile;
                    });

            adminProfile.setMember(member);

            profileRepository.save(adminProfile);

        }
    }

    @Transactional
    public void saveDefaultUser() { // 테스트 유저 아이디 아이디: test1, 비밀 번호 test123!
        for (int i = 1; i <= 5; i++) {
            String userName = "test" + i;
            if (memberRepository.findByUserName(userName).isEmpty()) {
                Member member = new Member();
                member.setUserName(userName);
                member.setPassword(passwordEncoder.encode("test123!"));
                member.setEmail(userName + "@gmail.com");
                member.setPhoneNum("000-0000-0000");
                member.setRole("ROLE_USER");
                member.setRealName("테스트 유저" + i);
                member.setNickName("테스트 유저" + i);
                member.setCreateDate(LocalDateTime.now());

                memberRepository.save(member);
            }
        }
    }
    public void delete(Member member){
        memberRepository.delete(member);
    }
    public void save(Member member){
        memberRepository.save(member);
    }





}
