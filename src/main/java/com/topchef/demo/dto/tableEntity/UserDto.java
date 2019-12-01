package com.topchef.demo.dto.tableEntity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String createDate;
}
