package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.service.MasterService;
import com.deliverygig.moonjyoung.vo.delivery.UnivTimeVO;

@RestController
public class MasterController {
    @Autowired
    MasterService mService;

    // @PostMapping("/univ/add")
    // public ResponseEntity<Object> postUnivAdd(@RequestParam("univ") String univ, Model model) {
    //     Map<String, Object> resultMap = mService.addUniv(univ);
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }

    @PostMapping("/location/add")
    public ResponseEntity<Object> postLocationAdd(@RequestParam("univ") String univ, @RequestParam("pua") String pua,
            Model model) {
        Map<String, Object> resultMap = mService.addPickUpArea(univ, pua);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    @PostMapping("/univTime/add")
    public ResponseEntity<Object> postUnivTimeAdd(@RequestBody UnivTimeVO data, @RequestParam Long seq) {
        Map<String, Object> resultMap = mService.addUnivTime(data, seq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    @PostMapping("/storeStatus/update")
    public ResponseEntity<Object> storeStatusUpdate(@RequestParam Integer status) {
        Map<String, Object> resultMap = mService.updateStatus(status);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
}