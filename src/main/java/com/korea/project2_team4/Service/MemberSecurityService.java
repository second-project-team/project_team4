package com.korea.project2_team4.Service;


import com.korea.project2_team4.Config.UserRole;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Repository.MemberRepository;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Builder
public class MemberSecurityService implements UserDetailsService {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <Member> member1 = this.memberRepository.findByUserName(username);
        if (member1.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member member = member1.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }
        else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(member.getUserName(), member.getPassword(), authorities);
    }
}