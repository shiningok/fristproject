package com.deliverygig.moonjyoung.api.adminpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/master")
public class PageMainController {
    @GetMapping("")
    public String getIndex(HttpSession session) {
        if(session.getAttribute("loginUser") != null) {
            return "redirect:/master/main" ;
        }
        return "/admin/index"; 
    }
    @GetMapping("/main")
    public String getMain() {
        return "/admin/main";
    }
    @GetMapping("/index")
    public String getIndex2(HttpSession session) {
        if(session.getAttribute("loginUser") != null) {
            session.invalidate();
        }
        return "/admin/index";
    }
}
