package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverygig.moonjyoung.entity.food.FoodCategoryEntity;
import com.deliverygig.moonjyoung.repository.food.FoodCategoryRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.foodCategory.CateAddVO;
import com.deliverygig.moonjyoung.vo.foodCategory.CateListVO;
import com.deliverygig.moonjyoung.vo.foodCategory.CateUpdateVO;

import jakarta.transaction.Transactional;
import lombok.Builder;

@Service
@Builder
public class FoodCategoryService {
    @Autowired FoodCategoryRepository fcRepo;
    @Autowired StoreInfoRepository    siRepo;
    //create
    public Map<String, Object> addCategory(CateAddVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(fcRepo.countByFcName(data.getFcName())==1) {
            resultMap.put("status", false);
            resultMap.put("message", data.getFcName() + "은/는 이미 존제하는 카테고리이름 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        else {
        FoodCategoryEntity fcEntity = FoodCategoryEntity.builder()
            .fcName(data.getFcName())
            .storeInfoEntity(siRepo.findBySiSeq(data.getFcSiSeq())) //.fcSiSeq(data.getFcSiSeq())
            .fcOrder(data.getFcOrder())
            .build();   

        fcRepo.save(fcEntity);
        resultMap.put("status", true);
        resultMap.put("message", "등록이 완료되었습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
        }
    }

    // read
    public Map<String, Object> getCateList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<CateListVO> returnList = new ArrayList<CateListVO>();
        for(FoodCategoryEntity data : fcRepo.findAll()) {
            CateListVO clVO = new CateListVO();
            clVO.setFcSeq(data.getFcSeq());
            clVO.setFcName(data.getFcName());
            clVO.setFcSiSeq(data.getStoreInfoEntity().getSiSeq());
            clVO.setFcOrder(data.getFcOrder());

            returnList.add(clVO);
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("message" ,"조회 성공");
        resultMap.put("returnList" ,returnList);

        return resultMap;
    }
    // update
    public Map<String, Object> cateUpdate(Long seq, CateUpdateVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(fcRepo.findByFcSeq(seq)==null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "seq번호를 확인하세요.");
            return resultMap;
        }
        else {
            if(fcRepo.countByFcName(data.getFcName()) == 1) {
                resultMap.put("status", false);
                resultMap.put("message", data.getFcName() + "은/는 이미 존재하는 카테고리명 입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else{
                FoodCategoryEntity entity = fcRepo.findByFcSeq(seq);
                entity.setFcName(data.getFcName());
                entity.setStoreInfoEntity(siRepo.findBySiSeq(data.getFcSiSeq()));
                entity.setFcOrder(data.getFcOrder());
                
                fcRepo.save(entity);
                resultMap.put("status", true);
                resultMap.put("message", "수정이 완료되었습니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
                return resultMap;
            }
        }
    }

    //delete
    @Transactional
    public Map<String, Object> dCate(@RequestBody FoodCategoryEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (fcRepo.countByFcSeq(data.getFcSeq()) == 1) {
            fcRepo.deleteByFcSeq(data.getFcSeq());
            resultMap.put("status", true);
            resultMap.put("message", "삭제 되었습니다.");
            resultMap.put("code", HttpStatus.OK);
            return resultMap;
        }
        resultMap.put("status", false);
        resultMap.put("message", "카테고리 Seq 오류입니다.");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
        return resultMap;
    }
    

    
}