package com.deliverygig.moonjyoung.api.adminpage.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;

import jakarta.transaction.Transactional;

@Service
public class PickUpAreaService {
  @Autowired PickUpAreaRepository pRepository;
  @Autowired UnivInfoRepository univInfoRepository;

  public Map<String, Object> getPickUpList(String keyword, Pageable pageable) {
    Page<PickUpAreaEntity> page = pRepository.findByPuaNameContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }
  
  public Map<String, Object> addPickupArea(String name, Long uiSeq) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    PickUpAreaVO vo = new PickUpAreaVO();
    vo.setPuaName(name);
    vo.setUniv(univInfoRepository.findById(uiSeq).get());
    if (name==null || name.equals("")) {
        resultMap.put("status", false);
        resultMap.put("message", "올바른 수령장소를 입력해주세요.");
        return resultMap;
    }
    else if (pRepository.countByPuaName(name)!=0) {
        resultMap.put("status", false);
        resultMap.put("message", "이미 있는 수령장소 입니다.");
        return resultMap;
    }
    else {
        pRepository.save(vo.toPickupEntity());
        resultMap.put("status", true);
        resultMap.put("message", "새 장소 추가 성공");
    }
    return resultMap;
  }

    @Transactional
    public void deletePickupArea(Long pickup_no) {
      pRepository.deleteById(pickup_no);
    }
  }
