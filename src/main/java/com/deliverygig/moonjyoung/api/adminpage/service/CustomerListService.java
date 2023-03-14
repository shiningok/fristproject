package com.deliverygig.moonjyoung.api.adminpage.service;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class CustomerListService {
  @Autowired CustomerRepository ctRepo;

  public Map<String, Object> getCustomerList(String keyword, Pageable pageable) {
    Page<CustomerInfoEntity> page = ctRepo.findByCiIdContains(keyword, pageable);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("list", page.getContent());
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }

  @Transactional
  public void deleteCustomer(Long ciSeq) {
    ctRepo.deleteById(ciSeq);
  }
}