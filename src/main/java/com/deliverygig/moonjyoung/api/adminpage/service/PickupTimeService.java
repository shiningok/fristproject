package com.deliverygig.moonjyoung.api.adminpage.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.UnivTimeVO;

import jakarta.transaction.Transactional;

@Service
public class PickupTimeService {
  @Autowired UnivTimeInfoRepository univTimeInfoRepository;
  @Autowired UnivInfoRepository univInfoRepository;

  public Map<String, Object> getPickupTimeList(String keyword, Pageable pageable) {
    Page<UnivTimeInfoEntity> page = univTimeInfoRepository.findByUtiNameContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }

  public Map<String, Object> addPickupTime(String name, Long uiSeq, LocalTime closeTime, LocalTime deliTime1, LocalTime deliTime2) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    UnivTimeVO vo = new UnivTimeVO();
    vo.setUiSeq(uiSeq);
    vo.setTimeName(name);
    vo.setCloseTime(closeTime);
    vo.setDeliTime1(deliTime1);
    vo.setDeliTime2(deliTime2);
    if (name==null || name.equals("")) {
        resultMap.put("status", false);
        resultMap.put("message", "올바른 시간이름을 입력해주세요.");
        return resultMap;
    }
    else if (univTimeInfoRepository.countByUtiName(name)!=0) {
        resultMap.put("status", false);
        resultMap.put("message", "이미 있는 배달시간이름 입니다.");
        return resultMap;
    }
    else {
        UnivTimeInfoEntity entity = new UnivTimeInfoEntity();
        entity.setUtiUiSeq(uiSeq);
        entity.setUtiName(vo.getTimeName());
        entity.setUtiCloseTime(vo.getCloseTime());
        entity.setUtiPickupTime1(vo.getDeliTime1());
        entity.setUtiPickupTime2(vo.getDeliTime2());
        univTimeInfoRepository.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "새 시간 추가 성공");
    }
    return resultMap;
  }

  @Transactional
  public void deletePickupTime(Long pickupTime_no) {
    univTimeInfoRepository.deleteById(pickupTime_no);
  }
}
