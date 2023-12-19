package com.korea.project2_team4.Config.OAuth2;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

    String getImage();
}