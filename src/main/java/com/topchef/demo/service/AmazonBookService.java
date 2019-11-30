package com.topchef.demo.service;

import com.topchef.demo.domain.AmazonBaseline;
import com.topchef.demo.dto.handlesEntity.ProductDetailDto;
import com.topchef.demo.exception.ItemNotFoundException;
import com.topchef.demo.repository.AmazonBaselineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AmazonBookService {

    @Autowired
    private AmazonBaselineRepository amazonBaselineRepository;
//    @Autowired
//    private AmazonBaselineCategoryRepository amazonBaselineCategoryRepository;
//    @Autowired
//    private AmazonReviewsRepository amazonReviewsRepository;

//    public List<AmazonBookDto> getAllItems() {
//        Set<String> bookCategoryIds = amazonBaselineCategoryRepository.findAllByCategory("Books")
//                .stream()
//                .map(AmazonBaselineCategory::getItemId)
//                .collect(Collectors.toSet());
//        List<AmazonBaseline> allItems = amazonBaselineRepository.findAll();
//        return allItems.stream()
//                .filter(item -> bookCategoryIds.contains(item.getId()))
//                .map(amazonBook -> {
//                    AmazonBookDto dto = new AmazonBookDto();
//                    BeanUtils.copyProperties(amazonBook, dto);
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    public ProductDetailDto getOneItem(String id) {

        AmazonBaseline oneItem = amazonBaselineRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        ProductDetailDto dto = new ProductDetailDto();
        BeanUtils.copyProperties(oneItem, dto);
        return dto;
    }


}
