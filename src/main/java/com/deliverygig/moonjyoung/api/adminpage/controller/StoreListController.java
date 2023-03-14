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

import com.deliverygig.moonjyoung.api.adminpage.service.StoreListService;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/store")
public class StoreListController {
  @Autowired StoreListService storeService;
  @Autowired StoreInfoRepository sRepository;
  
  @GetMapping("/list")
  public String getGenreList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
  (size = 10, sort = "siStatus", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
    if (keyword == null) keyword = "";
    model.addAttribute("result", storeService.getStoreList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/admin/university/storeList";
  }
  
  @GetMapping("/status")
  public String getStoreStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
    StoreInfoEntity entity = sRepository.findBySiSeq(seq);
    entity.setSiStatus(status);
    sRepository.save(entity);
    return "redirect:/store/list";
  }

  @GetMapping("/delete")
  public String storeDelete(@RequestParam Long seq) {
    StoreInfoEntity entity = sRepository.findBySiSeq(seq);
    sRepository.delete(entity);
    return "redirect:/store/list";
  }
}
