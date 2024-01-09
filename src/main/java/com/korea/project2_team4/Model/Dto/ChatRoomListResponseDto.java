package com.korea.project2_team4.Model.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomListResponseDto {
    private String roomName;
    @Builder
    public ChatRoomListResponseDto(String roomName){
        this.roomName = roomName;
    }

}