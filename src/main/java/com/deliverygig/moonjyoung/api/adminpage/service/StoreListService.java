package com.deliverygig.moonjyoung.api.adminpage.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;

@Service
public class StoreListService {
  @Autowired StoreInfoRepository sRepository;
  
  public Map<String, Object> getStoreList(String keyword, Pageable pageable) {
    Page<StoreInfoEntity> page = sRepository.findBySiNameContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }

  // public Map<String, Object> getStoreStatusUpdate(Long siSeq, Integer status) {
  //   Map<String, Object> map = new LinkedHashMap<String, Object>();
  //   StoreInfoEntity entity = sRepository.findBySiSeq(siSeq);
  //   entity.setSiStatus(status);
  //   map.put("entity", entity);
  //   return map;
  // }
}
