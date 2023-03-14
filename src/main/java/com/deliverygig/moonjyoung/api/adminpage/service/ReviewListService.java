package com.deliverygig.moonjyoung.api.adminpage.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.review.ReviewEntity;
import com.deliverygig.moonjyoung.repository.review.ReviewRepository;

@Service
public class ReviewListService {
  @Autowired ReviewRepository reviewRepository;

  public Map<String, Object> getReviewlist(String keyword, Pageable pageable) {
    Page<ReviewEntity> page = reviewRepository.findByriContentsContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }
}
