package com.topchef.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowFollowerDto {
    private String followerId;
    private String publisherId;
    private String followerName;
}
