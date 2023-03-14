package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.vo.optionconnect.AddConnectVO;

import lombok.Builder;

//@Service
//@Builder
//public class FoodOptionConnectService {
//    @Autowired FoodOptionConnectRepository focRepo;
//    @Autowired FoodMenuInfoRepository fRepo;
//    
//    // create
//    public Map<String, Object> addConnect(AddConnectVO data) {
//        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//        FoodOptionConnectEntity focEntity = FoodOptionConnectEntity.builder()
//            .focFmoOrder(data.getFocFmoOrder())
//            .FoodMenuInfoEntity(fRepo.findByFocFmiSeq(data.getFocFmiSeq()))
//            .focFmoSeq(data.getFocFmoSeq())
//            .build();
//    }
//    @JoinColumn(name = "foc_fmi_seq") private FoodMenuInfoEntity foodMenuInfoEntity;
//    @ManyToOne @JoinColumn(name = "foc_fmo_seq") private FoodMenuOptionEntity foodMenuOptionEntity;
//    // read
//    // update
//    // delete
//}