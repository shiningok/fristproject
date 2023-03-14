package com.deliverygig.moonjyoung.api.adminpage.controller;

import java.time.LocalTime;
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

import com.deliverygig.moonjyoung.api.adminpage.service.PickupTimeService;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pickupTime")
public class PickupTimeController {
  @Autowired PickupTimeService pickupTimeService;
  @Autowired UnivInfoRepository univInfoRepository;

  @GetMapping("/list")
  public String getPickupTimeList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
  (size = 10, sort = "utiSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
    if (keyword == null) keyword = "";
    model.addAttribute("result", pickupTimeService.getPickupTimeList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/admin/university/universityDetail";
  }

  @GetMapping("/add")
  public String getPickupTimeAdd(@RequestParam Long univ_no, Model model) {
    model.addAttribute("univ_no", univ_no);
    return "/admin/university/pickupTimeAdd";
  }
  @PostMapping("/add")
  public String postTimeAdd(String name, Long univ_no, LocalTime closeTime, LocalTime deliTime1, LocalTime deliTime2, Model model) {
    Map<String, Object> resultMap = pickupTimeService.addPickupTime(name, univ_no, closeTime, deliTime1, deliTime2);
    if((Boolean)resultMap.get("status")) {
      return "redirect:/univ/detail?univ_no="+univ_no;
    }
    else {
      model.addAttribute("univ_no", univ_no);
      model.addAttribute("name", name);
      model.addAttribute("closeTime", closeTime);
      model.addAttribute("deliTime1", deliTime1);
      model.addAttribute("deliTime2", deliTime2);
      model.addAttribute("result", resultMap);
      return "/admin/university/pickupTimeAdd";
    }
  }

  @GetMapping("/delete")
  public String getPickupTimeDelete(@RequestParam Long pickupTime_no) {
    pickupTimeService.deletePickupTime(pickupTime_no);
    return "redirect:/univ/list";
  }
}
