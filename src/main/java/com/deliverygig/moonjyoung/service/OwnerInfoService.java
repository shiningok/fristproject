package com.deliverygig.moonjyoung.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;
import com.deliverygig.moonjyoung.vo.account.JoinOwnerVO;
import com.deliverygig.moonjyoung.vo.account.LoginOwnerInfoVO;
import com.deliverygig.moonjyoung.vo.account.LoginOwnerVO;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.Builder;

@Builder
@Service
public class OwnerInfoService {
    @Autowired OwnerRepository oRepo;

    public Map<String, Object> addOwner (JoinOwnerVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (oRepo.countByOiId(data.getOiId())  == 1) {
            resultMap.put("status" , false);
            resultMap.put("message", data.getOiId() + "은/는 이미 존재하는 ID 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(oRepo.countByOiNickName(data.getOiNickName()) == 1) {    
            resultMap.put("status" , false);
            resultMap.put("message", data.getOiNickName() + "은/는 이미 존재하는 nickname 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(oRepo.countByOiEmail(data.getOiEmail())==1) {
            resultMap.put("status" , false);
            resultMap.put("message", data.getOiEmail() + "은/는 이미 존재하는 E-mail 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(oRepo.countByOiPhone(data.getOiPhone())==1) {
            resultMap.put("status" , false);
            resultMap.put("message", data.getOiPhone()  + "은/는 이미 존재하는 phone number 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else {
            OwnerEntity entity = OwnerEntity.builder()
                .oiEmail(data.getOiEmail())
                .oiId(data.getOiId())
                .oiPwd(data.getOiPwd())
                .oiNickName(data.getOiNickName())
                .oiPhone(data.getOiPhone())
                .oiJoinDt(LocalDate.now())
                .oiStatus(1)
                .build();
            oRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "회원이 등록되었습니다.");
            resultMap.put("code", HttpStatus.CREATED);
        }
        return resultMap; 
    }

    //로그인
    public Map<String, Object> loginOwner (LoginOwnerVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity Ownerlogin = oRepo.findByOiIdAndOiPwd(data.getOwner_id(), data.getOwner_pwd()); 
        if(Ownerlogin == null) {
            resultMap.put("status" , false);
            resultMap.put("message", "아이디 또는 비밀번호의 오류입니다");
        }
        else if(Ownerlogin.getOiStatus() == 2 ) { // (1.정상 2 블라인드 3. 가입대기)
            resultMap.put("status", false);
            resultMap.put("message", "블라인드 처리된 계정입니다.");
        }
        else if(Ownerlogin.getOiStatus() == 3 ) {
            resultMap.put("status", false);
            resultMap.put("message", "가입대기중 입니다.");
        }
        else {
            resultMap.put("status" , true);
            resultMap.put("message", "로그인이 완료되었습니다");
            resultMap.put("login", new LoginOwnerInfoVO(Ownerlogin));
        }
        return resultMap;
    }

    // 로그아웃
    public Map<String, Object> logoutOwner (HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (session.getAttribute("loginOwner") == null) {
            resultMap.put("status" , false);
            resultMap.put("message", "로그인 먼저 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
            session.invalidate();
            resultMap.put("status", true);
            resultMap.put("message", "로그아웃 되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
    }
    
//    // 로그아웃
// public Map<String, Object> logoutOwner(OwnerLoginVO data) {
//     Map<String, Object> resultMap = new LinkedHashMap<String,Oject>();
//     OwnerEntity Ownerlogout = oRepo.findByOiIdAndOiPwd(data.getId(), data.getPwd());
//     if(Ownerlogout == ) {
//         resultMap.put("status" , false);
//         resultMap.put("message", "아이디 또는 비밀번호의 오류입니다");
//         resultMap.put("code", HttpStatus.BAD_REQUEST);
//     }
//     else {
//         resultMap.put("status" , true);
//         resultMap.put("message", "로그아웃 되었습니다.");
//         resultMap.put("code", HttpStatus.ACCEPTED);
//     }
// }





// 회원수정
// 비밀번호 수정
// http://localhost:8080/owner/update/oiPwd?value=123
public Map<String, Object> updatePwd(String value, HttpSession session) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    OwnerEntity loginOwner = (OwnerEntity)session.getAttribute("loginOwner");
    if (loginOwner == null) {
        resultMap.put("status", false);
        resultMap.put("message", "로그인이 필요합니다.");
        resultMap.put("code", HttpStatus.FORBIDDEN);
    }
    else {
        loginOwner.setOiPwd(value);
        oRepo.save(loginOwner);
        resultMap.put("status", true);
        resultMap.put("message", "pwd가 수정되었습니다.");
        resultMap.put("code", HttpStatus.OK);
    }
    return resultMap;
}   

// 닉네임 수정
// http://localhost:8080/owner/update/oiNickName?value=123
public Map<String, Object> updateNickname(String value, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity loginOwner = (OwnerEntity)session.getAttribute("loginOwner");
        if (loginOwner == null) {
            resultMap.put("status", false);
            resultMap.put("message", "로그인이 필요합니다.");
            resultMap.put("code", HttpStatus.FORBIDDEN);
        }
        else {
            loginOwner.setOiNickName(value);
            oRepo.save(loginOwner);
            resultMap.put("status", true);
            resultMap.put("message", "nickname이 수정되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        }
        return resultMap;
    }

    // 이메일 수정
    // http://localhost:8080/owner/update/oiEmail?value=호징어@이웃.컴
    public Map<String, Object> updateEmail(String value, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity loginOwner = (OwnerEntity)session.getAttribute("loginOwner");
        String email_pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        Pattern e = Pattern.compile(email_pattern);
        if (loginOwner == null) {
            resultMap.put("status", false);
            resultMap.put("message", "로그인이 필요합니다.");
            resultMap.put("code", HttpStatus.FORBIDDEN);
            return resultMap;
        }
        else if (!e.matcher(value).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "올바르지 않은 이메일 형식입니다. (******@********)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
        }
            loginOwner.setOiEmail(value);
            oRepo.save(loginOwner);
            resultMap.put("status", true);
            resultMap.put("message", "email이 수정되었습니다.");
            resultMap.put("code", HttpStatus.OK);
            return resultMap;
    }
    // 폰번호 수정
    // http://localhost:8080/owner/update/oiPhone?value=010-5757-5757
    public Map<String, Object> updatePhone(String value, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity loginOwner = (OwnerEntity) session.getAttribute("loginOwner");
        String phone_pattern = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
        Pattern p = Pattern.compile(phone_pattern);
        if (loginOwner == null) {
            resultMap.put("status", false);
            resultMap.put("message", "로그인이 필요합니다.");
            resultMap.put("code", HttpStatus.FORBIDDEN);
            return resultMap;
        }
        else if(!p.matcher(value).matches()){
                resultMap.put("status", "false");
                resultMap.put("message", "올바르지 않은 폰 번호입니다. (01x-xxxx-xxxx)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;  
        }
            loginOwner.setOiPhone(value);
            oRepo.save(loginOwner);
            resultMap.put("status", true);
            resultMap.put("message", "phone number 가 수정되었습니다.");
            resultMap.put("code", HttpStatus.OK);
            return resultMap;
        
    }
    // 회원 탈퇴
    @Transactional
    public Map<String, Object> deleteOwner(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        OwnerEntity loginOwner = (OwnerEntity)session.getAttribute("loginOwner");
        if (loginOwner == null) {
            resultMap.put("status", false);
            resultMap.put("message", "로그인이 필요합니다.");
            resultMap.put("code", HttpStatus.FORBIDDEN);
        }
        else {
            oRepo.deleteByOiSeq(loginOwner.getOiSeq());
            resultMap.put("status", true);
            resultMap.put("message", "탈퇴되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        }
        return resultMap;
    }







// 수정
// public Map<String, Object> SujeongOwner(OwnerSujeongVO data) {
//     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


// }

// 탈퇴
// public Map<String, Object> TaltoeOwner()

}

