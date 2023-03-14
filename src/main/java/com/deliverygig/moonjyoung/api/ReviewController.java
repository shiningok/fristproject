package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.ReviewService;
import com.deliverygig.moonjyoung.vo.review.AddReviewVO;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService rService;
    @PostMapping("/add")
    public ResponseEntity<Object> addReview(@RequestBody AddReviewVO data) {
        Map<String, Object> resultMap = rService.addReview(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    @GetMapping("/list")
    public ResponseEntity<Object> storeReviewList(@RequestParam Long siSeq) {
        Map<String, Object> resultMap = rService.getStoreReview(siSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    @GetMapping("/mylist")
    public ResponseEntity<Object> myReviewList(@RequestParam Long ciSeq) {
        Map<String, Object> resultMap = rService.getMyReview(ciSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
}
