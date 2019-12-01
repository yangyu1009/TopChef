package com.topchef.demo.dto.tableEntity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFollowDto {
    private String publisherId;
    private String followerId;
}
