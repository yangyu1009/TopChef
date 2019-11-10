package com.topchef.demo.rest;

import com.topchef.demo.dto.ProductDetailDto;
import com.topchef.demo.service.AmazonBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/book")
public class BookController {
    private AmazonBookService amazonBookService;

    public BookController(AmazonBookService amazonBookService) {
        this.amazonBookService = amazonBookService;
    }
//    @GetMapping(path = "/all")
//    public List<AmazonBookDto> getAllItems() {
//        return amazonBookService.getAllItems();
//    }
    @GetMapping(path = "/user/{id}")
    public ProductDetailDto getOneItem(@PathVariable("id") String id) {
        return amazonBookService.getOneItem(id);
    }
//    @GetMapping(path = "/user/review/{id}")
//    public List<ProductReviewDto> getAllReview(@PathVariable("id") String id) { return amazonBookService.getReview(id);}
}