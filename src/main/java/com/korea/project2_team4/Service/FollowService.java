package com.korea.project2_team4.Service;


import com.korea.project2_team4.Model.Entity.Follow;
import com.korea.project2_team4.Model.Entity.Member;
import com.korea.project2_team4.Repository.FollowRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Builder
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberService memberService;

    public void follow(Long followerId, Long followingId) {
        Member follower = memberService.getMemberById(followerId);
        Member following = memberService.getMemberById(followingId);

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);
    }

    public void unfollow(Long followerId, Long followingId) {
        Member follower = memberService.getMemberById(followerId);
        Member following = memberService.getMemberById(followingId);

        followRepository.deleteByFollowerAndFollowing(follower, following);
    }

    public List<Member> getFollowers(Long memberId) {
        Member member = memberService.getMemberById(memberId);
        List<Follow> followers = followRepository.findByFollowing(member);

        return followers.stream()
                .map(Follow::getFollower)
                .collect(Collectors.toList());
    }

    public List<Member> getFollowing(Long memberId) {
        Member member = memberService.getMemberById(memberId);
        List<Follow> following = followRepository.findByFollower(member);

        return following.stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }



}
