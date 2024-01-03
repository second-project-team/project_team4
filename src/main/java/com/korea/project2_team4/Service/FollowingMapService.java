package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.FollowingMap;
import com.korea.project2_team4.Model.Entity.Profile;
import com.korea.project2_team4.Repository.FollowingMapRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Builder
public class FollowingMapService {
    private final FollowingMapRepository followingMapRepository;

    public void setfollowingMap(Profile follower, Profile followee){
        FollowingMap followingMap = new FollowingMap();
        followingMap.setFollower(follower);
        followingMap.setFollowee(followee);
        this.followingMapRepository.save(followingMap);
    }

    public void deletefollowingMap(Profile follower, Profile followee){
        FollowingMap unfollowingMap = this.followingMapRepository.findByProfiles(follower.getId(), followee.getId());
        this.followingMapRepository.delete(unfollowingMap);
    }

    public List<Profile> getMyfollowers(Profile profile){
        List<FollowingMap> mapList = this.followingMapRepository.findAllByMyFollowers(profile.getId()); //내팔로워찾는거 팔로이 에서 찾아야댐
        List<Profile> followerList = new ArrayList<>();
        for (FollowingMap followingMap : mapList) {
            Profile follower = followingMap.getFollower();
            followerList.add(follower);
        }
        return followerList;
    }

    public List<Profile> getMyfollowings(Profile profile) { //내가팔로우하는사람 찾는거 팔로우에서 찾아야댐
        List<FollowingMap> mapList = this.followingMapRepository.findAllFollowings(profile.getId());
        List<Profile> followingList = new ArrayList<>();
        for (FollowingMap followingMap : mapList) {
            Profile following = followingMap.getFollowee();
            followingList.add(following);
        }
        return followingList;
    }
}
