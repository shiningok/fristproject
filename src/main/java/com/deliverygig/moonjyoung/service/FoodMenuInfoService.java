package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.repository.food.FoodCategoryRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.image.FoodImageRepository;
import com.deliverygig.moonjyoung.vo.food.FoodAddVO;
import com.deliverygig.moonjyoung.vo.food.FoodListVO;
import com.deliverygig.moonjyoung.vo.food.FoodUpdateVO;
import com.deliverygig.moonjyoung.vo.food.ShowFoodListVO;

import jakarta.transaction.Transactional;
import lombok.Builder;

@Service
@Builder
public class FoodMenuInfoService {
    @Autowired FoodMenuInfoRepository fRepo;
    // @Autowired FoodEntity fEntity;
    @Autowired FoodCategoryRepository fcRepo;
    @Autowired FoodImageRepository foodImageRepository;

    // Create
    public Map<String, Object> addFood(FoodAddVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (fRepo.countByFmiName(data.getFmiName()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", data.getFmiName() + "은/는 이미 존재하는 메뉴이름 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        else {
        FoodMenuInfoEntity fEntity = FoodMenuInfoEntity.builder()
                .fmiName(data.getFmiName())
                .fmiExplanation(data.getFmiExplanation())
                .fmiPrice(data.getFmiPrice())
                .foodCategoryEntity(fcRepo.findByFcSeq(data.getFmiFcSeq()))
                .fmiBest(data.getFmiBest())
                .build();

        fRepo.save(fEntity);
        resultMap.put("status", true);
        resultMap.put("message", "등록이 완료되었습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
        }
    }

    // Read
    public Map<String, Object> getList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<FoodListVO> returnList = new ArrayList<FoodListVO>();
        for (FoodMenuInfoEntity data : fRepo.findAll()) {
            FoodListVO flVO = new FoodListVO();
            flVO.setFmiSeq(data.getFmiSeq());
            flVO.setFmiName(data.getFmiName());
            flVO.setFmiExplanation(data.getFmiExplanation());
            flVO.setFmiPrice(data.getFmiPrice());
            flVO.setFmiFcSeq(data.getFoodCategoryEntity().getFcSeq());
            flVO.setFmiBest(data.getFmiBest());

            

            returnList.add(flVO);

        }

        // data = fRepo.count();
        // resultMap.put("total_food_number", returnList);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("message", "조회 성공");
        resultMap.put("returnList", returnList);

        return resultMap;
        
    }

    // Update
    public Map<String, Object> modifyFood(Long seq, FoodUpdateVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (fRepo.findByFmiSeq(seq) == null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "seq번호를 확인하세요.");
            return resultMap;
        } 
        else{
            if (fRepo.countByFmiName(data.getFmiName()) == 1) {
                resultMap.put("status", false);
                resultMap.put("message", data.getFmiName() + "은/는 이미 존재하는 메뉴이름 입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else{
                // entity.builder()
                // .fmiName(data.getFmiName())
                // .fmiExplanation(data.getFmiExplanation())
                // .fmiPrice(data.getFmiPrice())
                // .fmiBest(data.getFmiBest())
                // .build();
                FoodMenuInfoEntity entity = fRepo.findByFmiSeq(seq);
                entity.setFmiName(data.getFmiName());
                entity.setFmiExplanation(data.getFmiExplanation());
                entity.setFmiPrice(data.getFmiPrice());
                entity.setFmiBest(data.getFmiBest());
                
                fRepo.save(entity);
                resultMap.put("status", true);
                resultMap.put("message", "수정이 완료되었습니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
                return resultMap;
            }
        }
    }

    // Delete
    @Transactional
    public Map<String, Object> dFood(@RequestBody FoodMenuInfoEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(fRepo.countByFmiSeq(data.getFmiSeq()) == 1) {
            fRepo.deleteByFmiSeq(data.getFmiSeq());
            resultMap.put("status", true);
            resultMap.put("message", "삭제 되었습니다.");
            resultMap.put("code", HttpStatus.OK);
            return resultMap;
        }
        resultMap.put("status", false);
        resultMap.put("message", "메뉴이름 Seq 오류입니다.");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
        return resultMap;
    }

}



// 중복체크 - 음식메뉴이름

// 가격
// 메뉴 카테고리 번호
// 대표메뉴여부 (1 true / 2 false)
