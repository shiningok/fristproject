package com.deliverygig.moonjyoung.api.adminpage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deliverygig.moonjyoung.api.adminpage.service.UniversityListService;

import org.springframework.data.domain.Sort;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/univ")
public class UniversityListController {
  @Autowired UniversityListService uService;
  
  @GetMapping("/list")
  public String getGenreList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
  (size = 10, sort = "uiSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
    if (keyword == null) keyword = "";
    model.addAttribute("result", uService.getUnivList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/admin/university/universityList";
  }
  
  @GetMapping("/add")
  public String getUnivAdd() {
    return "/admin/university/universityAdd";
  }

  @PostMapping("/add")
  public String postUnivAdd(String univ, Model model) {
    Map<String, Object> resultMap = uService.addUniv(univ);
    if((Boolean)resultMap.get("status")) {
      return "redirect:/univ/list";
    }
    else {
      model.addAttribute("univ", univ);
      model.addAttribute("result", resultMap);
      return "/admin/university/universityAdd";
    }
  }

  @GetMapping("/detail")
  public String updateUnivInfo(@RequestParam Long univ_no, @RequestParam @Nullable Integer page, @RequestParam @Nullable String keyword,
  Model model){
      if(page == null) page = 0;
      if(keyword == null) keyword = "";
      Map<String, Object> map = uService.selectUnivInfo(univ_no);
      map.put("message", null);
      model.addAttribute("univ", map);
      model.addAttribute("page", page);
      model.addAttribute("keyword", keyword);
      model.addAttribute("univ_no", univ_no);
      return "/admin/university/universityDetail";
  }

  @GetMapping("/delete")
  public String getUnivDelete(@RequestParam Long univ_no) {
      uService.deleteUniv(univ_no);
      return "redirect:/univ/list";
  }

  // @GetMapping("/update")
  // public String postUnivNameUpdate(@RequestParam Long no, Model model) {
    // model.addAttribute("no", no);
    // return "/admin/university/universityEdit";
  // }
  // @PostMapping("/update")
  // public String postUnivNameUpdate(Long no, String univ, Model model) {
    // Map<String, Object> resultMap = uService.updateUnivInfo(no, univ);
    // if((Boolean)resultMap.get("updated")) {
      // return "redirect:/univ/list";
    // }
    // else {
      // model.addAttribute("univ", univ);
      // model.addAttribute("result", resultMap);
      // return "/admin/university/universityEdit";
    // }
  // }

  // @PostMapping("/location/add")
  // public String postLocationAdd(@RequestParam("univ") String univ, @RequestParam("pua") String pua,
  //         Model model) {
  //     Map<String, Object> resultMap = uService.addPickUpArea(univ, pua);
  //     return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
  // }
  
  // @PostMapping("/univTime/add")
  // public String postUnivTimeAdd(@RequestBody UnivTimeVO data, @RequestParam Long seq) {
  //     Map<String, Object> resultMap = uService.addUnivTime(data, seq); 
  //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  // }
}
