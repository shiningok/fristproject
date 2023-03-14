package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Nullable;
import com.deliverygig.moonjyoung.service.VOService;


@RestController
@RequestMapping("/list")
public class VOController {
    @Autowired VOService voService;
    // 배달 장소 & 시간대 정보 목록
    // @GetMapping("/location/list")
    // public ResponseEntity<Object> getUnivPickUpAreaTime() {
    //     Map<String, Object> resultMap = voService.getLocationList();
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }
    // @GetMapping("/store/list")
    // public ResponseEntity<Object> getStoreUnivTime() {
    //     Map<String, Object> resultMap = voService.getStoreUnivTime();
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }
    // @GetMapping("/pickup/list")
    // public ResponseEntity<Object> getUnivPickUpArea() {
    //     Map<String, Object> resultMap = voService.getPickUpArea();
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }

    // 등록된 대학 리스트 조회
    @GetMapping("/univ")
    public ResponseEntity<Object> getUnivList() {
        Map<String, Object> resultMap = voService.getUnivList();
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @GetMapping("/pickuparea")
    public ResponseEntity<Object> getPuaList(@RequestParam Long uiSeq) {
        Map<String, Object> resultMap = voService.getpuaList(uiSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @GetMapping("/deliverytime")
    public ResponseEntity<Object> getUnivTimeList(@RequestParam Long uiSeq) {
        Map<String, Object> resultMap = voService.getUnivTimeList(uiSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @GetMapping("/store")
    public ResponseEntity<Object> getStoreList(@RequestParam Long utiSeq) {
        Map<String, Object> resultMap = voService.getStoreList(utiSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @GetMapping("/dcstore")
    public ResponseEntity<Object> getDCStoreList(@RequestParam Long utiSeq) {
        Map<String, Object> resultMap = voService.getDCStoreList(utiSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }



    @GetMapping("/univ/search")
    public ResponseEntity<Object> getUnivSearch(@RequestParam @Nullable String keyword) {
        Map<String, Object> resultMap = voService.searchUniv(keyword);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @GetMapping("/store/search")
    public ResponseEntity<Object> getStoreSearch(@RequestParam @Nullable String keyword) {
        Map<String, Object> resultMap = voService.searchStore(keyword);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    
     @GetMapping("/store/sort")
     public ResponseEntity<Object> getStoreListOrderByDiscount(@RequestParam Long utiSeq) {
         Map<String, Object> resultMap = voService.getOrderByStoreList(utiSeq);
         return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
     }
    
     

}

