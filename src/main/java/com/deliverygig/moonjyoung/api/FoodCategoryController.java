package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.entity.food.FoodCategoryEntity;
import com.deliverygig.moonjyoung.service.FoodCategoryService;
import com.deliverygig.moonjyoung.vo.foodCategory.CateAddVO;
import com.deliverygig.moonjyoung.vo.foodCategory.CateUpdateVO;

@RestController
@RequestMapping("/foodCategory")
public class FoodCategoryController {
    @Autowired FoodCategoryService fcService;

    // Create
    @PostMapping("/insert")
    public ResponseEntity<Object> cateInsert(@RequestBody CateAddVO data) {
        Map<String, Object> resultMap = fcService.addCategory(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // Read
    @GetMapping("/list")
    public ResponseEntity<Object> cateList() {
        Map<String, Object> resultMap = fcService.getCateList();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // Update
    @PostMapping("/update")
    public ResponseEntity<Object> categoryUpdate(@RequestParam Long seq, @RequestBody CateUpdateVO data) {
        Map<String, Object> resultMap = fcService.cateUpdate(seq, data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // Delete
    @PostMapping("/delete")
    public ResponseEntity<Object> deleteCate(@RequestBody FoodCategoryEntity data) {
        Map<String, Object> resultMap = fcService.dCate(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
