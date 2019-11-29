package com.topchef.demo.service;


import com.topchef.demo.domain.TopChefUser;
import com.topchef.demo.dto.UserDto;
import com.topchef.demo.repository.TopChefUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopChefUserService {
    @Autowired
    TopChefUserRepository topChefUserRepository;
    public List<UserDto> getAllUserInfo() {
        List<TopChefUser> allUserInfos = topChefUserRepository.getAllUserInfo();
        return allUserInfos.stream()
                .map(userInfo -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(userInfo, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
