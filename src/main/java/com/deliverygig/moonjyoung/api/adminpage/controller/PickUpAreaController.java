package com.deliverygig.moonjyoung.api.adminpage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.api.adminpage.service.PickUpAreaService;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pickup")
public class PickUpAreaController {
  @Autowired PickUpAreaService pService;
  @Autowired UnivInfoRepository univRepository;

  @GetMapping("/list")
  public String getGenreList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
  (size = 10, sort = "puaSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
    if (keyword == null) keyword = "";
    model.addAttribute("result", pService.getPickUpList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/admin/university/universityDetail";
  }

  @GetMapping("/add")
  public String getPickupAreaAdd(@RequestParam Long univ_no, Model model) {
    model.addAttribute("univ_no", univ_no);
    return "/admin/university/pickupPlaceAdd";
  }
  @PostMapping("/add")
  public String postUnivAdd(String name, Long univ_no, Model model) {
    Map<String, Object> resultMap = pService.addPickupArea(name, univ_no);
    if((Boolean)resultMap.get("status")) {
      return "redirect:/univ/detail?univ_no="+univ_no;
    }
    else {
      model.addAttribute("name", name);
      model.addAttribute("result", resultMap);
      return "/admin/university/pickupPlaceAdd";
    }
  }
    
  @GetMapping("/delete")
  public String getPickupDelete(@RequestParam Long pickup_no) {
    pService.deletePickupArea(pickup_no);
    return "redirect:/univ/list";
  }
}
