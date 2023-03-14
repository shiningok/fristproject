package com.deliverygig.moonjyoung.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.review.ReviewEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.repository.mycart.BasketMenuOptionsCombineRepository;
import com.deliverygig.moonjyoung.repository.review.ReviewRepository;
import com.deliverygig.moonjyoung.vo.review.AddReviewVO;
import com.deliverygig.moonjyoung.vo.review.StoreReviewListVO;

@Service
public class ReviewService {
    @Autowired CustomerRepository customerRepository;
    @Autowired
    ReviewRepository rRepo;
    @Autowired
    BasketMenuOptionsCombineRepository combineRepo;

    // 리뷰 등록
    public Map<String, Object> addReview(AddReviewVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (combineRepo.countByBmocSeq(data.getOrder_seq()) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "잘못된 주문번호입니다. ");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        if (rRepo.countByRiBmocSeq(data.getOrder_seq()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", "해당 주문번호의 리뷰가 이미 존재합니다. ");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        ReviewEntity entity = new ReviewEntity();
        entity.setRiContents(data.getRi_contents());
        entity.setRiScore(data.getRi_score());
        entity.setBasketMenuOptionsCombineEntity(combineRepo.findById(data.getOrder_seq()).get());
        rRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "리뷰가 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }

    // 가게 리뷰 조회 
    public Map<String, Object> getStoreReview(Long siSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreReviewListVO> returnList = new ArrayList<StoreReviewListVO>();
        if (rRepo.findAllBySiSeqAndRiStatus(siSeq, 1).size()==0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "조건을 만족하는 리뷰가 없습니다");
        }
        else {
            for (ReviewEntity data : rRepo.findAllBySiSeqAndRiStatus(siSeq, 1)) {
                StoreReviewListVO vo = new StoreReviewListVO();
                // customerInfo 유효성검사 임시 땜빵
                if (data.getBasketMenuOptionsCombineEntity().getBasketInfoEntity().getCustomerInfoEntity()==null) {
                    continue;
                }
                vo.setRiSeq(data.getRiSeq());
                vo.setCiNickName(data.getBasketMenuOptionsCombineEntity().getBasketInfoEntity().getCustomerInfoEntity().getCiNickName());
                vo.setMenu(data.getBasketMenuOptionsCombineEntity().getFoodMenuInfoEntity().getFmiName());
                vo.setMenuOption(data.getBasketMenuOptionsCombineEntity().getBmocOptionAll());
                vo.setReviewScore(data.getRiScore());
                vo.setReviewContent(data.getRiContents());
                vo.setReviewRegDt(data.getRiRegDt().toLocalDate());
                returnList.add(vo);
            }
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("message", "조회 완료");
            resultMap.put("list", returnList);
        }
        return resultMap;
    }

    // 내 리뷰 조회 
    public Map<String, Object> getMyReview(Long ciSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<StoreReviewListVO> returnList = new ArrayList<StoreReviewListVO>();
        if (customerRepository.findById(ciSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "존재하지 않는 회원입니다");
        }
        else if (rRepo.findAllByCiSeq(ciSeq).size()==0) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            resultMap.put("message", "작성한 리뷰가 없습니다");
        }
        else {
            for (ReviewEntity data : rRepo.findAllByCiSeq(ciSeq)) {
                StoreReviewListVO vo = new StoreReviewListVO();
    
                vo.setRiSeq(data.getRiSeq());
                vo.setCiNickName(data.getBasketMenuOptionsCombineEntity().getBasketInfoEntity().getCustomerInfoEntity().getCiNickName());
                vo.setMenu(data.getBasketMenuOptionsCombineEntity().getFoodMenuInfoEntity().getFmiName());
                vo.setMenuOption(data.getBasketMenuOptionsCombineEntity().getBmocOptionAll());
                vo.setReviewScore(data.getRiScore());
                vo.setReviewContent(data.getRiContents());
                vo.setReviewRegDt(data.getRiRegDt().toLocalDate());
    
                returnList.add(vo);
            }
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("message", "조회 완료");
            resultMap.put("list", returnList);
        }
        return resultMap;
    }
}
