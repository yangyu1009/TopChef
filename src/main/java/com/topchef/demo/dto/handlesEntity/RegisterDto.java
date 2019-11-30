package com.topchef.demo.dto.handlesEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {
    private String uerId;
    private String userName;
    private String password;
    private String email;
    private String createTime;
}
