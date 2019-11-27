package com.topchef.demo.rest;

import com.topchef.demo.dto.ProductDetailDto;
import com.topchef.demo.dto.ReciptDto;
import com.topchef.demo.service.AmazonBookService;
import com.topchef.demo.service.TopChefReciptService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @CrossOrigin
    @RestController
    @RequestMapping(path = "/recipt")
    public class ReciptController {
        private TopChefReciptService topChefReciptService;

        public ReciptController(TopChefReciptService topChefReciptService) {
            this.topChefReciptService = topChefReciptService;
        }
        @GetMapping(path = "/all")
        public List<ReciptDto> getAllItems() {
        return topChefReciptService.getAllItems();
    }
        @GetMapping(path = "/item/{id}")
        public ReciptDto getOneItem(@PathVariable("id") String id) {
            return topChefReciptService.getOneItem(id);
        }
//    @GetMapping(path = "/user/review/{id}")
//    public List<ProductReviewDto> getAllReview(@PathVariable("id") String id) { return amazonBookService.getReview(id);}
    }

