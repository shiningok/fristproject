package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;
import com.deliverygig.moonjyoung.vo.delivery.UnivTimeVO;


@Service
public class MasterService {
    @Autowired PickUpAreaRepository pickUpAreaRepository;
    @Autowired UnivInfoRepository univInfoRepository;
    @Autowired
    UnivTimeInfoRepository univTimeInfoRepository;

    public Map<String, Object> addUniv(String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        PickUpAreaVO vo = new PickUpAreaVO();
        vo.setUiName(name);
        // vo.setPuaName("("+name+")수령장소");

        if (name==null || name.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "올바른 대학명을 입력해주세요.");
            return resultMap;
        }
        else if (univInfoRepository.countByUiName(name)!=0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "이미 있는 대학명입니다.");
            return resultMap;
        }
        else {
            univInfoRepository.save(vo.toUnivInfoEntity());
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.CREATED);
            resultMap.put("message", "새 대학 추가 성공");
        }
        
        return resultMap;
    }

    public Map<String, Object> addPickUpArea(String univ, String pickUpArea) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        PickUpAreaEntity pickUpAreaEntity = new PickUpAreaEntity();
        UnivInfoEntity data = univInfoRepository.findByUiName(univ);
        Long univ_no = data.getUiSeq();
        if (univInfoRepository.findByUiName(univ) == null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "존재하지 않는 대학입니다.");
            return resultMap;
        } 
        else if (pickUpArea == null || pickUpArea.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "올바른 수령장소를 입력해주세요");
            return resultMap;
        }
        else if(pickUpAreaRepository.findByPuaSeqAndPuaName(univ_no, pickUpArea) != null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "이미 존재하는 수령장소입니다.");
            return resultMap;
        }
        else {
            UnivInfoEntity entity = univInfoRepository.findByUiName(univ);
            pickUpAreaRepository
                    .save(pickUpAreaEntity.builder().puaSeq(null).puaName(pickUpArea).univInfoEntity(entity).build());
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.CREATED);
        resultMap.put("message", "새 수령장소 추가 성공");
        return resultMap;
    }

    // 대학별 배달시간 정보 등록 
    public Map<String, Object> addUnivTime(UnivTimeVO data, Long seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (univInfoRepository.findByUiSeq(seq) == null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "존재하지 않는 대학입니다.");
            return resultMap;
        } else if (univTimeInfoRepository.countByUtiCloseTime(data.getCloseTime()) == 1) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "이미 존재하는 주문마감시간입니다.");
            return resultMap;
        }
        UnivTimeInfoEntity entity = new UnivTimeInfoEntity();
        entity.setUnivInfoEntity(univInfoRepository.findByUiSeq(seq));
        entity.setUtiName(data.getTimeName());
        entity.setUtiPickupTime1(data.getDeliTime1());
        entity.setUtiPickupTime2(data.getDeliTime2());
        entity.setUtiCloseTime(data.getCloseTime());
        univTimeInfoRepository.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "대학별 배달시간 정보가 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }
    // 가게 상태값 변경 
    public Map<String, Object> updateStatus(Integer status) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StoreInfoEntity data = new StoreInfoEntity();
        data.setSiStatus(status);
        resultMap.put("status", true);
        resultMap.put("message", "가게 상태값이 변경되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }
}