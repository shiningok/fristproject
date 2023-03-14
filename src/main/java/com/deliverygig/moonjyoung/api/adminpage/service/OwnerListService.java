package com.deliverygig.moonjyoung.api.adminpage.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;

import jakarta.transaction.Transactional;

@Service
public class OwnerListService {
  @Autowired OwnerRepository owRepo;

  public Map<String, Object> getOwnerList(String keyword, Pageable pageable) {
    Page<OwnerEntity> page = owRepo.findByOiIdContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }
  
  @Transactional
  public void deleteOwner(Long oiSeq) {
    owRepo.deleteById(oiSeq);
  }
}
