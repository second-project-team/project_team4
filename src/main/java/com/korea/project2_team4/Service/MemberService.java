package com.korea.project2_team4.Service;

import com.korea.project2_team4.Repository.MemberRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class MemberService {
    private final MemberRepository memberRepository;

}
