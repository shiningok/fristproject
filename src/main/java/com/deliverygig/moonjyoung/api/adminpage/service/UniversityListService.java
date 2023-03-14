package com.deliverygig.moonjyoung.api.adminpage.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.PickUpAreaVO;
import com.deliverygig.moonjyoung.vo.delivery.UnivTimeVO;

import jakarta.transaction.Transactional;

@Service
public class UniversityListService {
  @Autowired PickUpAreaRepository pickUpAreaRepository;
  @Autowired UnivInfoRepository univInfoRepository;
  @Autowired UnivTimeInfoRepository univTimeInfoRepository;

  public Map<String, Object> getUnivList(String keyword, Pageable pageable) {
    Page<UnivInfoEntity> page = univInfoRepository.findByUiNameContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }

  public Map<String, Object> addUniv(String name) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    PickUpAreaVO vo = new PickUpAreaVO();
    vo.setUiName(name);
    // vo.setPuaName("("+name+")수령장소");
    if (name==null || name.equals("")) {
        resultMap.put("status", false);
        resultMap.put("message", "올바른 대학명을 입력해주세요.");
        return resultMap;
    }
    else if (univInfoRepository.countByUiName(name)!=0) {
        resultMap.put("status", false);
        resultMap.put("message", "이미 있는 대학명입니다.");
        return resultMap;
    }
    else {
        univInfoRepository.save(vo.toUnivInfoEntity());
        resultMap.put("status", true);
        resultMap.put("message", "새 대학 추가 성공");
    }
    return resultMap;
  }

  // public Map<String, Object> updateUnivInfo(Long no, String univ) {
    // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // Optional<UnivInfoEntity> entityOpt = univInfoRepository.findById(no);
    // if(entityOpt.isEmpty()) {
      // resultMap.put("updated", false);
      // resultMap.put("no", no);
      // resultMap.put("univ", univ);
      // resultMap.put("message", "잘못된 대학교 정보입니다.");
    // }
    // else if(entityOpt.get().getUiName().equalsIgnoreCase(univ)) {
      // resultMap.put("updated", false);
      // resultMap.put("no", no);
      // resultMap.put("univ", univ);
      // resultMap.put("message", "기존 설정된 이름으로 변경 불가능합니다.");
    // }
    // else if(entityOpt.get().getUiName().equals("")) {
      // resultMap.put("updated", false);
      // resultMap.put("no", no);
      // resultMap.put("univ", univ);
      // resultMap.put("message", "공백으로는 변경 불가능합니다.");
    // }
    // else if(univInfoRepository.countByUiName(univ) != 0) {
      // resultMap.put("updated", false);
      // resultMap.put("no", no);
      // resultMap.put("univ", univ);
      // resultMap.put("message", univ+"학교는 이미 등록된 학교입니다.");
    // }
    // else {
      // UnivInfoEntity entity = UnivInfoEntity.builder().uiSeq(no).uiName(univ).build();
      // univInfoRepository.save(entity);
      // resultMap.put("updated", true);
    // }
    // return resultMap;
  // }

  public Map<String, Object> selectUnivInfo(Long univ_no) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    Optional<UnivInfoEntity> entityOpt = univInfoRepository.findById(univ_no);
    List<UnivTimeInfoEntity> list = new ArrayList<UnivTimeInfoEntity>();
    list = univTimeInfoRepository.findAllByUtiUiSeq(univ_no);
    if(entityOpt.isEmpty()) {
      resultMap.put("status", false);
    }
    else {
      resultMap.put("status", true);
      resultMap.put("no", entityOpt.get().getUiSeq());
      resultMap.put("univ", entityOpt.get().getUiName());
      resultMap.put("pickupAreas", entityOpt.get().getPuaEntityList());
      resultMap.put("pickupTimes", list);
    }
    return resultMap;
  }

  @Transactional
  public void deleteUniv(Long univ_no) {
    univInfoRepository.deleteById(univ_no);
  }
  
  // public Map<String, Object> addPickUpArea(String univ, String pickUpArea) {
  //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  //     PickUpAreaEntity pickUpAreaEntity = new PickUpAreaEntity();
  //     UnivInfoEntity data = univInfoRepository.findByUiName(univ);
  //     Long univ_no = data.getUiSeq();
  //     if (univInfoRepository.findByUiName(univ) == null) {
  //         resultMap.put("status", false);
  //         resultMap.put("message", "존재하지 않는 대학입니다.");
  //         return resultMap;
  //     } 
  //     else if (pickUpArea == null || pickUpArea.equals("")) {
  //         resultMap.put("status", false);
  //         resultMap.put("message", "올바른 수령장소를 입력해주세요");
  //         return resultMap;
  //     }
  //     else if(pickUpAreaRepository.findByPuaSeqAndPuaName(univ_no, pickUpArea) != null) {
  //         resultMap.put("status", false);
  //         resultMap.put("message", "이미 존재하는 수령장소입니다.");
  //         return resultMap;
  //     }
  //     else {
  //         UnivInfoEntity entity = univInfoRepository.findByUiName(univ);
  //         pickUpAreaRepository.save(pickUpAreaEntity.builder().puaSeq(null).puaName(pickUpArea).univInfoEntity(entity).build());
  //     }
  //     resultMap.put("status", true);
  //     resultMap.put("message", "새 수령장소 추가 성공");
  //     return resultMap;
  // }

  // 대학별 배달시간 정보 등록 
  public Map<String, Object> addUnivTime(UnivTimeVO data,Long seq) {
      Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
      if (univInfoRepository.findByUiSeq(seq) == null) {
          resultMap.put("status", false);
          resultMap.put("message", "존재하지 않는 대학입니다.");
          return resultMap;
      }
      else if (univTimeInfoRepository.countByUtiCloseTime(data.getCloseTime()) == 1) {
          resultMap.put("status", false);
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
      return resultMap; 
  }


}
