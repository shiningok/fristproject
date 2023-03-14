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

import com.deliverygig.moonjyoung.api.adminpage.service.MasterListService;
import com.deliverygig.moonjyoung.entity.account.MasterEntity;
import com.deliverygig.moonjyoung.repository.account.MasterRepository;
import com.deliverygig.moonjyoung.vo.account.MasterAddVO;
import com.deliverygig.moonjyoung.vo.account.MasterLoginInfoVO;


import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/master")
public class MasterListController {
    @Autowired MasterRepository mrRepo;
    @Autowired MasterListService masterListService;

    @GetMapping("/list")
    public String getMasterList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
    (size = 10, sort = "miSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        if (keyword == null) keyword = "";
        model.addAttribute("result", masterListService.getMasterList(keyword, pageable));
        model.addAttribute("keyword", keyword);
        return "/admin/master/list";
    }


    @GetMapping("/add")
    public String getAddAdmin(){
        return "/admin/master/add";
    }
    @PostMapping("/add")
    public String postAddAdmin(MasterAddVO data, Model model) {
        Map<String, Object> map = masterListService.postAddAdmin(data);
        if((boolean)map.get("status")){
            return "redirect:/master/login";
        }
        model.addAttribute("inputdata", data);
        model.addAttribute("message", map.get("message"));
        return "/admin/master/add";
    }

    @GetMapping("/login") 
    public String postMasterLogin() {
        return "/admin/index";
    }

    @PostMapping("/login")
    public String postMasterLogin(MasterLoginInfoVO login, HttpSession session, Model model) {
        Map<String, Object> map = masterListService.postMasterLogin(login);
        if(map.get("loginUser") == null) {
            model.addAttribute("message", map.get("message"));
            model.addAttribute("loginStatus", map.get("loginStatus"));
            return "/admin/index";
        }
        else {
            session.setAttribute("loginUser", map.get("loginUser"));
            return "redirect:/master/main";
        }
    }

    @GetMapping("/status")
    public String getMemberStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        MasterEntity entity = mrRepo.findByMiSeq(seq);
        entity.setMiStatus(status);
        mrRepo.save(entity);
        return "redirect:/master/list";
    }

    @GetMapping("/delete")
    public String memberDelete(@RequestParam Long miSeq) {
        masterListService.deleteMaster(miSeq);
        return "redirect:/master/list";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/master";
    }
}
