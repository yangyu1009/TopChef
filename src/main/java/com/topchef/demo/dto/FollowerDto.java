package com.topchef.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowerDto {
    private String publishId;
    private String followerId;
}
