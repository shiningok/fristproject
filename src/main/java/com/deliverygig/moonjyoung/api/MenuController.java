package com.deliverygig.moonjyoung.api;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.deliverygig.moonjyoung.service.FoodMenuOptionService;
import com.deliverygig.moonjyoung.service.ShowFoodService;
import com.deliverygig.moonjyoung.service.VOService;

import jakarta.annotation.Nullable;

@RestController
@RequestMapping("/store/menu")
public class MenuController {
    @Autowired VOService voService;
    @Autowired ShowFoodService showFoodService;
    @Autowired
    FoodMenuOptionService MenuOptionService;

    // 가게 메뉴리스트 조회
    @GetMapping("/list")
    public ResponseEntity<Object> getMenuList(@RequestParam Long siSeq) {
        Map<String, Object> resultMap = showFoodService.getMenuList(siSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // 메뉴 옵션리스트 조회
    @GetMapping("/selectOption")
    public ResponseEntity<Object> getMenuOptionList(@RequestParam Long menuSeq) {
        Map<String, Object> resultMap = voService.getMenuOptionList(menuSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    
    // 메뉴 카테고리 옵션 등록 
    @PostMapping("/cateOption")
    public ResponseEntity<Object> MenuCateOptionAdd(@RequestParam String menucate,
            @RequestParam @Nullable Integer duplicated, @RequestParam @Nullable Integer required) {
        Map<String, Object> resultMap = MenuOptionService.addFoodMenuOption(menucate, duplicated, required);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 가게 카테 옵션 수정 
     @PostMapping("/cateOption/update")
    public ResponseEntity<Object> MenuCateOptionUpdate(@RequestParam Long fmo_seq, @RequestParam @Nullable String menucate,
            @RequestParam @Nullable Integer duplicated, @RequestParam @Nullable Integer required) {
        Map<String, Object> resultMap = MenuOptionService.updateFoodMenuOption(fmo_seq, menucate, duplicated, required);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    // 가게 카테 옵션 삭제 
      @PostMapping("/cateOption/delete")
    public ResponseEntity<Object> MenuCateOptionDelete(@RequestParam Long fmo_seq) {
        Map<String, Object> resultMap = MenuOptionService.DeleteFoodMenuOption(fmo_seq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 메뉴 카테고리 옵션 디테일 등록
    @PostMapping("/cateOption/detail")
    public ResponseEntity<Object> MenuCateOptionAdd(@RequestParam Long fmo_seq,
            @RequestParam Integer price, @RequestParam @Nullable Integer order, @RequestParam String name) {
        Map<String, Object> resultMap = MenuOptionService.addFoodMenuOptionDetail(fmo_seq, name, price, order);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 메뉴 카테고리 옵션 디테일 수정
    @PostMapping("/cateOption/detail/update")
    public ResponseEntity<Object> MenuCateOptionUpdate(@RequestParam Long fdo_seq,
            @RequestParam @Nullable Integer price, @RequestParam @Nullable Integer order, @RequestParam @Nullable String name) {
        Map<String, Object> resultMap = MenuOptionService.updateMenuOptionDetail(fdo_seq, name, price, order);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 메뉴 카테고리 옵션 디테일 삭제
    @PostMapping("/CateOption/detail/delete")
     public ResponseEntity<Object> MenuCateOptionDetailDelete(@RequestParam Long fdo_seq) {
         Map<String, Object> resultMap = MenuOptionService.DeleteMenuOptionDetail(fdo_seq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
   

    // 옵션 연결 테이블 등록 
    @PostMapping("/option/connect")
    public ResponseEntity<Object> OptionConnectAdd(@RequestParam Long foc_fmi_seq,
            @RequestParam Long foc_fmo_seq, @RequestParam Integer foc_fmo_order) {
        Map<String, Object> resultMap = MenuOptionService.addFoodOptionTableConnect(foc_fmi_seq, foc_fmo_seq,
                foc_fmo_order);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 옵션 연결 테이블 수정
    @PostMapping("/option/connect/update")
    public ResponseEntity<Object> OptionConnectUpdate(@RequestParam Long foc_seq, @RequestParam @Nullable Long foc_fmi_seq,
            @RequestParam @Nullable Long foc_fmo_seq, @RequestParam @Nullable Integer foc_fmo_order) {
        Map<String, Object> resultMap = MenuOptionService.UpdateFoodOptionTableConnect(foc_seq, foc_fmi_seq,
                foc_fmo_seq, foc_fmo_order);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // 옵션 연결 테이블 삭제
    @PostMapping("/option/connect/delete")
    public ResponseEntity<Object> OptionConnectDelete(@RequestParam Long foc_seq) {
        Map<String, Object> resultMap = MenuOptionService.DeleteFoodOptionTableConnect(foc_seq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    
}
