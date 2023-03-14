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

import com.deliverygig.moonjyoung.api.adminpage.service.OwnerListService;
import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/owner")
public class OwnerListController {
    @Autowired OwnerRepository owRepo;
    @Autowired OwnerListService ownerListService;

    @GetMapping("/list")
    public String getOwnerList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
    (size = 10, sort = "oiSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        if (keyword == null) keyword = "";
        model.addAttribute("result", ownerListService.getOwnerList(keyword, pageable));
        model.addAttribute("keyword", keyword);
        return "/admin/owner/list";
    }

    @GetMapping("/delete")
    public String getOwnerDelete(@RequestParam Long oiSeq) {
        ownerListService.deleteOwner(oiSeq);
        return "redirect:/owner/list";
    }

    @GetMapping("/status")
    public String getOwnerStatusUpdate(@RequestParam Long seq, @RequestParam Integer status) {
        OwnerEntity entity = owRepo.findByOiSeq(seq);
        entity.setOiStatus(status);
        owRepo.save(entity);
        return "redirect:/owner/list";
    }
}
