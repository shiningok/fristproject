package com.deliverygig.moonjyoung.api;

import java.rmi.server.ObjID;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.repository.food.FoodCategoryRepository;
import com.deliverygig.moonjyoung.service.FoodMenuInfoService;
import com.deliverygig.moonjyoung.vo.food.FoodAddVO;
import com.deliverygig.moonjyoung.vo.food.FoodUpdateVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/food")
public class FoodInfoController {
    @Autowired FoodMenuInfoService fService;
    @Autowired FoodCategoryRepository foodCategoryRepository;

    // C
    @PostMapping("/insert")
    public ResponseEntity<Object> foodInsert(@RequestBody FoodAddVO data, HttpSession session) {
        Map<String, Object> resultMap = fService.addFood(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // R
    @GetMapping("/list")
    public ResponseEntity<Object> getFoodList() {
        Map<String, Object> resultMap = fService.getList();
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // U
    @PostMapping("/update")
    public ResponseEntity<Object> foodUpdate(@RequestParam Long seq,
            @RequestBody FoodUpdateVO data) {
        Map<String, Object> resultMap = fService.modifyFood(seq,data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));

    }

    // D
    @PostMapping("/delete")
    public ResponseEntity<Object> deleteFood(@RequestBody FoodMenuInfoEntity data) {
        Map<String, Object> resultMap = fService.dFood(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // R
    // @GetMapping("/read")
    // public ResponseEntity<Object> getFoodList(Long data) {
    // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // return new ResponseEntity<Object>(resultMap,
    // (HttpStatus)resultMap.get("code"));
    // }

    // U
    // @PatchMapping("/update")
    // public ResponseEntity<Object> foodUpdate(@RequestBody FoodEntity data
    // ,HttpSession session) {
    // Map<String, Object> resultMap = fService.modifyFood(data);
    // return new ResponseEntity<Object>(resultMap,
    // (HttpStatus)resultMap.get("code"));
    // }
}
