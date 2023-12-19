package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entity.Follow;
import com.korea.project2_team4.Model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollowing(Member member);

    List<Follow> findByFollower(Member member);

    void deleteByFollowerAndFollowing(Member follower, Member following);
}
