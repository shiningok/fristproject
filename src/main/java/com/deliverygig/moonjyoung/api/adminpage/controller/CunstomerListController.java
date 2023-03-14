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

import com.deliverygig.moonjyoung.api.adminpage.service.CustomerListService;
import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CunstomerListController {
    @Autowired CustomerRepository ctRepo;
    @Autowired CustomerListService customerListService;

    @GetMapping("/list")
    public String getCustomerList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
    (size = 10, sort = "ciSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        if (keyword == null) keyword = "";
        model.addAttribute("result", customerListService.getCustomerList(keyword, pageable));
        model.addAttribute("keyword", keyword);
        return "/admin/customer/list";
    }

    @GetMapping("/delete")
    public String getCustomerDelete(@RequestParam Long ciSeq) {
        customerListService.deleteCustomer(ciSeq);
        return "redirect:/customer/list";
    }

    @GetMapping("/status")
    public String getCustomerStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        CustomerInfoEntity entity = ctRepo.findByCiSeq(seq);
        entity.setCiStatus(status);
        ctRepo.save(entity);
        return "redirect:/customer/list";
    }
}
