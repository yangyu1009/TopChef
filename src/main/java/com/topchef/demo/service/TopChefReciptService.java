package com.topchef.demo.service;

import com.topchef.demo.domain.TopChefRecipe;
import com.topchef.demo.dto.ReciptDto;
import com.topchef.demo.exception.ItemNotFoundException;
import com.topchef.demo.repository.TopChefReciptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;



@Service
@Transactional
public class TopChefReciptService {
    @Autowired
    private TopChefReciptRepository topChefReciptRepository;

        public List<ReciptDto> getAllItems() {
        List<TopChefRecipe> allRecipts = topChefReciptRepository.findAll();

        return allRecipts.stream()
                .map(recipt -> {
                    ReciptDto dto = new ReciptDto();
                    BeanUtils.copyProperties(recipt, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public ReciptDto getOneItem(String id) {

        TopChefRecipe oneItem = topChefReciptRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        ReciptDto dto = new ReciptDto();
        BeanUtils.copyProperties(oneItem, dto);
        return dto;
    }
}
