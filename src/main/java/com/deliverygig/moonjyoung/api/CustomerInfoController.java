package com.deliverygig.moonjyoung.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.service.CustomerInfoService;
import com.deliverygig.moonjyoung.vo.account.CustomerAddVO;
import com.deliverygig.moonjyoung.vo.account.LoginUserVO;
import com.deliverygig.moonjyoung.vo.account.LoginVO;
import com.deliverygig.moonjyoung.vo.account.UpdateCustomerInfoVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class CustomerInfoController {
    @Autowired
    CustomerRepository cRepo;
    @Autowired CustomerInfoService cService;
    @PostMapping("/join")
    public ResponseEntity<Object> memberJoin(@RequestBody CustomerAddVO data) {
        Map<String, Object> resultMap = cService.addMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }

    @PostMapping("/login") 
    public ResponseEntity<Object> memberLogin(@RequestBody LoginVO data, HttpSession session) throws Exception{
        Map<String, Object> resultMap = cService.LoginMember(data, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));

    }
    @PostMapping("/logout")
    public ResponseEntity<Object> memberLogOut(HttpSession session) {
        Map<String, Object> resultMap = cService.LogOutMember(session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> memberDelete(/*@RequestBody LoginUserVO data2,*/@RequestParam Long ciSeq, HttpSession session) throws Exception {
        Map<String, Object> resultMap = cService.DeleteMember(ciSeq, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    // @PostMapping("/update")
    // public ResponseEntity<Object> memberUpdate(@RequestBody LoginUserVo data2, HttpSession session) throws Exception {
    //     Map<String, Object> resultMap = cService.UpdateMember(data2, session);
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    // }
    @PostMapping("/check/{type}")
    public ResponseEntity<Object> checkAvalible(@RequestBody CustomerAddVO data, @PathVariable String type) {
        Map<String, Object> resultMap = cService.AvailableMember(data, type);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    @PostMapping("/update/{type}")
    public ResponseEntity<Object> memberUpdate(@RequestBody UpdateCustomerInfoVO data2, @RequestParam Long ciSeq, @PathVariable String type) throws Exception{
        Map<String, Object> resultMap = cService.UpdateMember(data2, type, ciSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
    
    
    @GetMapping("/list")
    public ResponseEntity<Object> AccountMember(Long data) {
        Map<String, Object> resultMap = cService.CountMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
    }
}
