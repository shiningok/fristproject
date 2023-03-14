package com.deliverygig.moonjyoung.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.store.StoreClosedDayEntity;
import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.account.OwnerRepository;
import com.deliverygig.moonjyoung.repository.store.StoreClosedDayRepository;
import com.deliverygig.moonjyoung.repository.store.StoreDetailInfoRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.store.StoreClosedDayInfoVO;
import com.deliverygig.moonjyoung.vo.store.StoreDetailInfoVO;
import com.deliverygig.moonjyoung.vo.store.UpdateStoreDetailVO;


@Service
public class AddStoreDetailInfoService {
    @Autowired
    StoreDetailInfoRepository dRepo;
    @Autowired
    StoreInfoRepository sRepo;
    @Autowired
    OwnerRepository oRepo;

    @Autowired
    StoreClosedDayRepository cRepo;

    // 가게 디테일 정보 등록
    public Map<String, Object> addStoreDetail(StoreDetailInfoVO data, Long siSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // OwnerEntity data2 = (OwnerEntity) session.getAttribute("loginOwner");
        // StoreDetailInfoEntity entity2 = dRepo.findBySdiSeq(seq); // 수정할 때 필요함. 
        String phone_pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
        Pattern p = Pattern.compile(phone_pattern);
        StoreInfoEntity entity1 = sRepo.findBySiSeq(siSeq);

        // if (session.getAttribute("loginOwner") == null) {
        //     resultMap.put("message", "로그인 먼저 해주세요.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }

        if (entity1 == null) {
            resultMap.put("message", "가게 정보가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        // else if(entity1.getSiOiSeq() != data2.getOiSeq()){
        //      resultMap.put("message", "해당 회원의 가게정보가 아닙니다.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }
        else if (dRepo.countBySdiSiSeq(siSeq) == 1) {
            resultMap.put("message", "해당 가게의 상세정보가 이미 있습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        else if (data.getSdiMinOrderPrice() < 0 || data.getSdiDeliveryPrice() < 0) {
            resultMap.put("message", "알맞지 않은 최소주문금액이거나 배달비금액 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        } else if (data.getSdiDeliveryPrice() == null || data.getSdiMinOrderPrice() == null) {
            resultMap.put("message", "최소주문금액이나 배달비는 반드시 작성해주셔야 합니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        } else if (!p.matcher(data.getSdiPhoneNumber()).matches()) {
            resultMap.put("message", "알맞지 않은 전화번호입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        StoreDetailInfoEntity entity = new StoreDetailInfoEntity();
        entity.setSdiDeliveryPrice(data.getSdiDeliveryPrice()); // 배달비
        entity.setSdiMinOrderPrice(data.getSdiMinOrderPrice()); // 최소주문금액
        entity.setSdiOwnerWord(data.getSdiOwnerWord()); // 사장님 한마디
        // entity.getStoreInfoEntity().setSiSeq(seq); 
        // entity.Setsdinum(seq); 
        entity.setStoreInfoEntity(sRepo.findBySiSeq(siSeq)); // 가게 번호 
        entity.setSdiPhoneNumber(data.getSdiPhoneNumber()); // 폰 번호
        entity.setSdiAddress(data.getSdiAddress()); // 주소
        entity.setSdiOrigin(data.getSdiOrigin()); // 원산지 
        entity.setSdiOwnerName(data.getSdiOwnerName()); // 사업자 이름
        entity.setSdiStoreName(data.getSdiStoreName()); // 가게  상호명
        entity.setSdiBusinessNumber(data.getSdiAddress()); // 사업자 번호
        dRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "가게 디테일 정보가 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }
    // 디테일 수정 
    public Map<String, Object> updateStoreDetail(UpdateStoreDetailVO data, Long siSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // StoreInfoEntity entity = sRepo.findBySiSeq(seq);
        StoreDetailInfoEntity entity2 = dRepo.findBySdiSeq(siSeq); // 수정할 때 필요함. 
        String phone_pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
        Pattern p = Pattern.compile(phone_pattern);
        if (entity2 == null) {
            resultMap.put("status", false);
            resultMap.put("message", "가게 정보가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        //  아무것도 정보가 입력되지 않았을 경우 원래 그대로의 정보로 수정한다. 
        if (data.getNewsdiAddress() == null)
            data.setNewsdiAddress(entity2.getSdiAddress());
        if (data.getNewsdiBusinessNumber() == null)
            data.setNewsdiBusinessNumber(entity2.getSdiBusinessNumber());
        if (data.getNewsdiDeliveryPrice() == null)
            data.setNewsdiDeliveryPrice(entity2.getSdiDeliveryPrice());
        if (data.getNewsdiMinOrderPrice() == null)
            data.setNewsdiMinOrderPrice(entity2.getSdiMinOrderPrice());
        if (data.getNewsdiOrigin() == null)
            data.setNewsdiOrigin(entity2.getSdiOrigin());
        if (data.getNewsdiOwnerName() == null)
            data.setNewsdiOwnerName(entity2.getSdiOwnerName());
        if (data.getNewsdiOwnerWord() == null)
            data.setNewsdiOwnerWord(entity2.getSdiOwnerWord());
        if (data.getNewsdiStoreName() == null)
            data.setNewsdiStoreName(entity2.getSdiStoreName());
        if (data.getNewsdiPhoneNumber() == null)
            data.setNewsdiPhoneNumber(entity2.getSdiPhoneNumber());
        if (!p.matcher(data.getNewsdiPhoneNumber()).matches()) {
            resultMap.put("message", "알맞지 않은 전화번호입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        } else if (data.getNewsdiDeliveryPrice() < 0 || data.getNewsdiMinOrderPrice() < 0) {
            resultMap.put("message", "알맞지 않은 최소주문금액이거나 배달비금액 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }

        entity2.setSdiAddress(data.getNewsdiAddress());
        entity2.setSdiBusinessNumber(data.getNewsdiBusinessNumber());
        entity2.setSdiDeliveryPrice(data.getNewsdiDeliveryPrice());
        entity2.setSdiMinOrderPrice(data.getNewsdiMinOrderPrice());
        entity2.setSdiOrigin(data.getNewsdiOrigin());
        entity2.setSdiOwnerName(data.getNewsdiOrigin());
        entity2.setSdiStoreName(data.getNewsdiStoreName());
        entity2.setSdiPhoneNumber(data.getNewsdiPhoneNumber());
        entity2.setSdiOwnerWord(data.getNewsdiOwnerWord());

        dRepo.save(entity2);
        resultMap.put("status", true);
        resultMap.put("message", "가게 디테일 정보가 수정되었습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;

    }
    //  휴무일 등록 
    public Map<String, Object> addStoreClosedDay(String value,Long seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();  
        StoreClosedDayInfoVO data = new StoreClosedDayInfoVO();
        if (sRepo.findBySiSeq(seq) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "가게 정보가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        else if (value.equals("월")) {
            data.setScdi_day_no(1);
            data.setScdi_day(value);
        }
        else if (value.equals("화")) {
            data.setScdi_day_no(2);
            data.setScdi_day(value);
        }
        else if (value.equals("수")) {
            data.setScdi_day_no(3);
            data.setScdi_day(value);
        }
        else if (value.equals("목")) {
            data.setScdi_day_no(4);
            data.setScdi_day(value);
        }
        else if (value.equals("금")) {
            data.setScdi_day_no(5);
            data.setScdi_day(value);
        }
        else if (value.equals("토")) {
            data.setScdi_day_no(6);
            data.setScdi_day(value);
        }
        else if (value.equals("일")) {
            data.setScdi_day_no(7);
            data.setScdi_day(value);
        }
        else {
            resultMap.put("status", false);
            resultMap.put("message", "올바르지 않은 요일 형식입니다 ex ) 월");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        if (cRepo.findByScdiSeqAndScdiDay(seq, value) != null) {
            resultMap.put("status", false);
            resultMap.put("message", "이미 존재하는 휴무일입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        StoreClosedDayEntity entity = new StoreClosedDayEntity();
        entity.setScdiDay(data.getScdi_day());
        entity.setStoreInfoEntity(sRepo.findBySiSeq(seq));
        entity.setScdiDayNo(data.getScdi_day_no());
        cRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "휴무일이 등록되었습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
        
    }
}
