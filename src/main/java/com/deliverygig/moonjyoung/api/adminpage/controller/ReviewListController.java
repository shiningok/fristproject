package com.deliverygig.moonjyoung.api.adminpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.api.adminpage.service.ReviewListService;
import com.deliverygig.moonjyoung.entity.review.ReviewEntity;
import com.deliverygig.moonjyoung.repository.review.ReviewRepository;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reviewList")
public class ReviewListController {
  @Autowired ReviewListService reviewListService;
  @Autowired ReviewRepository reviewRepository;

  @GetMapping("/list")
  public String getReviewList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
  (size = 10, sort = "riSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
      if (keyword == null) keyword = "";
      model.addAttribute("result", reviewListService.getReviewlist(keyword, pageable));
      model.addAttribute("keyword", keyword);
      return "/admin/review/reviewList";
  }
  
  @GetMapping("/status")
  public String getOwnerStatusUpdate(@RequestParam Long riSeq, @RequestParam Integer status) {
      ReviewEntity entity = reviewRepository.findByRiSeq(riSeq);
      entity.setRiStatus(status);
      reviewRepository.save(entity);
      return "redirect:/reviewList/list";
  }
}
