package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.store.AddstoreInfoVo;
import com.deliverygig.moonjyoung.vo.store.UpdateStoreVO;

@Service
public class AddstoreInfoService {
    @Autowired
    StoreInfoRepository sRepo;
    @Autowired
    OwnerRepository oRepo;

    // 가게 기본정보 등록 
    public Map<String, Object> addStore(AddstoreInfoVo data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // OwnerEntity data2 = (OwnerEntity) session.getAttribute("loginOwner");
        // if (session.getAttribute("loginOwner") == null) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "로그인 먼저 해주세요.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }

        if (sRepo.countBySiName(data.getSiName()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", "이미 등록된 가게입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        else if (0 > data.getSiDiscount() || data.getSiDiscount() > 100) {
            resultMap.put("status", false);
            resultMap.put("message", "알맞지 않은 할인율입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        OwnerEntity entity1 = oRepo.findByOiSeq(data.getSiOiSeq());
        if (entity1 == null) {
            resultMap.put("status", false);
            resultMap.put("message", "알맞지 않은 회원번호 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        StoreInfoEntity entity = new StoreInfoEntity();
        entity.setSiDiscount(data.getSiDiscount());
        entity.setSiName(data.getSiName());
        entity.setSiOiSeq(data.getSiOiSeq());
        sRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "가게 등록이 완료되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        // resultMap.put("addStore", entity);
        return resultMap;
    }

    // 가게 기본정보 수정 
    public Map<String, Object> UpdateStore(UpdateStoreVO data, Long siSeq, String type) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // OwnerEntity data2 = (OwnerEntity) session.getAttribute("loginOwner");
        // if (session.getAttribute("loginOwner") == null) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "로그인 먼저 해주세요.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }
        //  가게 이름 수정
        if (type.equals("storeName")) {
            StoreInfoEntity entity = sRepo.findBySiSeq(siSeq);
            if (sRepo.countBySiName(data.getUpdatesiName()) == 1) {
                resultMap.put("status", false);
                resultMap.put("message", "이미 등록된 가게입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }

            else if (sRepo.findBySiSeq(siSeq) == null) {
                resultMap.put("status", false);
                resultMap.put("message", "가게 정보가 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            // else if (!entity.getSiOiSeq().equals(data2.getOiSeq())) {
            //     resultMap.put("status", false);
            //     resultMap.put("message", "해당 회원의 가게가 아닙니다.");
            //     resultMap.put("code", HttpStatus.BAD_REQUEST);
            //     return resultMap;
            // }
            entity.setSiName(data.getUpdatesiName());
            sRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "가게 이름 수정이 완료되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        // 가게 할인율 수정
         if (type.equals("storeDiscount")) {
             StoreInfoEntity entity = sRepo.findBySiSeq(siSeq);
            if (sRepo.findBySiSeq(siSeq) == null) {
                resultMap.put("status", false);
                resultMap.put("message", "가게 정보가 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            // else if (!entity.getSiOiSeq().equals(data2.getOiSeq())) {
            //     resultMap.put("status", false);
            //     resultMap.put("message", "해당 회원의 가게가 아닙니다.");
            //     resultMap.put("code", HttpStatus.BAD_REQUEST);
            //     return resultMap;
            // }
            else if (0 > data.getUpdatesiDiscount() || data.getUpdatesiDiscount() > 1) {
                resultMap.put("status", false);
                resultMap.put("message", "알맞지 않은 할인율입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            entity.setSiDiscount(data.getUpdatesiDiscount());
            sRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "가게 할인율 수정이 완료되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
    }
        return resultMap;
}
    

}