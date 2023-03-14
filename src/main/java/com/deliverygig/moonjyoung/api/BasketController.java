package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.BasketService;
import com.deliverygig.moonjyoung.vo.mycart.AddBasketMenuOptionVO;
import com.deliverygig.moonjyoung.vo.mycart.PutBasketInfoVO;

@RestController
public class BasketController {
    @Autowired BasketService basketService;

    @GetMapping("/order/history")
    public ResponseEntity<Object> getOrderHistory(@RequestParam Long ciSeq) {
        Map<String, Object> resultMap = basketService.getOrderHistory(ciSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @GetMapping("/basket")
    public ResponseEntity<Object> getBasket(@RequestParam Long ciSeq) {
        Map<String, Object> resultMap = basketService.getBasketInfo(ciSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/basket")
    public ResponseEntity<Object> postBasket(@RequestBody PutBasketInfoVO data) {
        Map<String, Object> resultMap = basketService.postBasketInfo(data.getCiSeq(), data.getPuaSeq());
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/order/basket")
    public ResponseEntity<Object> postBasketMenu(@RequestBody AddBasketMenuOptionVO data) {
        Map<String, Object> resultMap = basketService.getMenuOptions(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @DeleteMapping("/basket/delete")
    public ResponseEntity<Object> deleteBasketMenu(@RequestParam Long bmocSeq) {
        Map<String, Object> resultMap = basketService.deleteMenuOptions(bmocSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @DeleteMapping("/basket/deleteAll")
    public ResponseEntity<Object> deleteBasketMenuAll(@RequestParam Long ciSeq) {
        Map<String, Object> resultMap = basketService.deleteAllMenuOptions(ciSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
