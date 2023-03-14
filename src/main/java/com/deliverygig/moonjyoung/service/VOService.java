package com.deliverygig.moonjyoung.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;
import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;
import com.deliverygig.moonjyoung.entity.review.ReviewEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.repository.delivery.PickUpAreaRepository;
import com.deliverygig.moonjyoung.repository.delivery.StoreTimeDetailRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivInfoRepository;
import com.deliverygig.moonjyoung.repository.delivery.UnivTimeInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodDetailOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuInfoRepository;
import com.deliverygig.moonjyoung.repository.food.FoodMenuOptionRepository;
import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
import com.deliverygig.moonjyoung.repository.image.PickUpAreaImageRepository;
import com.deliverygig.moonjyoung.repository.image.StoreImageRepository;
import com.deliverygig.moonjyoung.repository.review.ReviewRepository;
import com.deliverygig.moonjyoung.repository.store.StoreDetailInfoRepository;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;
import com.deliverygig.moonjyoung.vo.delivery.ClosePickupTimeVO;
import com.deliverygig.moonjyoung.vo.delivery.ShowPuaVO;
import com.deliverygig.moonjyoung.vo.delivery.ShowUnivListVO;
import com.deliverygig.moonjyoung.vo.delivery.ShowUnivTimeVO;
import com.deliverygig.moonjyoung.vo.food.ShowFoodDetailOptionVO;
import com.deliverygig.moonjyoung.vo.food.ShowFoodOptionVO;
import com.deliverygig.moonjyoung.vo.food.ShowMenuDetailVO;
import com.deliverygig.moonjyoung.vo.store.ShowStoreInfoVO;
import com.deliverygig.moonjyoung.vo.store.ShowStoreListVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class VOService {
    @Autowired UnivInfoRepository univInfoRepository;
    @Autowired PickUpAreaRepository pickUpAreaRepository;
    @Autowired StoreInfoRepository storeInfoRepository;
    @Autowired StoreTimeDetailRepository storeTimeDetailRepository;
    @Autowired UnivTimeInfoRepository univTimeInfoRepository;
    @Autowired StoreDetailInfoRepository storeDetailInfoRepository;
    @Autowired PickUpAreaImageRepository pickUpAreaImageRepository;
    @Autowired StoreImageRepository storeImageRepository;
    @Autowired FoodMenuInfoRepository foodMenuInfoRepository;
    @Autowired FoodOptionConnectRepository foodOptionConnectRepository;
    @Autowired FoodMenuOptionRepository foodMenuOptionRepository;
    @Autowired FoodDetailOptionRepository foodDetailOptionRepository;
    @Autowired ReviewRepository reviewRepository;

//@Autowired StoreImageRepository
    // 이하 코드는 20230120 이후 코드.
    // 각 페이지별로 띄워줄 리스트 정보를 다를 예정

    // 대학리스트 조회에 사용
    public Map<String, Object> getUnivList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowUnivListVO> returnList = new ArrayList<ShowUnivListVO>();

        for (UnivInfoEntity data : univInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "uiName"))) {
            ShowUnivListVO vo = new ShowUnivListVO(data);
            returnList.add(vo);
    }
        
        resultMap.put("status", true);
        if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(등록된 대학이 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("list", returnList);
            return resultMap;
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("list", returnList);
            return resultMap;
        }
    }

    //localhost:8888/list/univ/search?keyword=대학
    public Map<String, Object> searchUniv(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (keyword==null || keyword.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "검색어를 입력하세요.");
        }
        else if (univInfoRepository.findByUiNameEquals(keyword)==null) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "해당하는 대학이 없습니다.");
        }
        else {
            ShowUnivListVO data = new ShowUnivListVO(univInfoRepository.findByUiNameEquals(keyword));
            resultMap.put("status", true);
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("message", "성공");
            resultMap.put("data", data);
        }
        return resultMap;
    }

    // 수령장소 조회에 사용
    public Map<String, Object> getpuaList(Long uiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowPuaVO> returnList = new ArrayList<ShowPuaVO>();

        for (PickUpAreaEntity data : pickUpAreaRepository.findAllByPuaUiSeq(uiSeq)) {
            ShowPuaVO vo = new ShowPuaVO(data);
            
            PickUpAreaImageEntity imgEntity = pickUpAreaImageRepository.findByPuaiPuaSeq(data.getPuaSeq());
            String img = "";
            if(imgEntity != null) {
                img = imgEntity.getPuaiUri();
            }
            vo.setPuaiUri(img);

            returnList.add(vo);
        }

        resultMap.put("status", true);
        if (univInfoRepository.countByUiSeq(uiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 대학입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(등록된 장소가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("univ", univInfoRepository.findByUiSeq(uiSeq).getUiName());
        resultMap.put("list", returnList);
        return resultMap;
    }

    // 배달시간 조회에 사용
    public Map<String, Object> getUnivTimeList(Long uiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowUnivTimeVO> returnList = new ArrayList<ShowUnivTimeVO>();

        for (UnivTimeInfoEntity data : univTimeInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "utiCloseTime"))) {
            if (data.getUnivInfoEntity().getUiSeq() == uiSeq) {
                ShowUnivTimeVO vo = new ShowUnivTimeVO(data);
                returnList.add(vo);
            }
        }

        resultMap.put("status", true);
        if (univInfoRepository.countByUiSeq(uiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 대학입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(등록된 시간이 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("univ", univInfoRepository.findByUiSeq(uiSeq).getUiName());
        resultMap.put("list", returnList);
        return resultMap;
    }

    // 배달시간별 가게 조회
    public Map<String, Object> getStoreList(Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> returnList = new ArrayList<ShowStoreListVO>();

        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAll()) {
            if (data.getUnivTimeInfoEntity().getUtiSeq() == utiSeq) {
                ShowStoreListVO vo = new ShowStoreListVO(data);
                vo.setReviewCount(reviewRepository.countBySiSeq(data.getStoreInfoEntity().getSiSeq()));
                if (reviewRepository.findAvgRiScoreBySiSeq(data.getStoreInfoEntity().getSiSeq())==null) {
                    vo.setScoreAvg(0.0);
                }
                else {
                    
                    vo.setScoreAvg(((int)(reviewRepository.findAvgRiScoreBySiSeq(data.getStoreInfoEntity().getSiSeq())*10))/10.0);
                }

                StoreImageEntity imgEntity = storeImageRepository.findBySimgSiSeqAndSimgDivision(data.getStoreInfoEntity().getSiSeq(), 9);
                String img = "";
                if(imgEntity != null) {
                    img = imgEntity.getSimgUri();
                    }
                        vo.setSimgUriLogo(img);
                
                returnList.add(vo);
            }
        }

        resultMap.put("status", true);
        if (univTimeInfoRepository.countByUtiSeq(utiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 시간정보 입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(배달하는 가게가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("timeName", univTimeInfoRepository.findByUtiSeq(utiSeq).getUtiName());
        resultMap.put("list", returnList);
        return resultMap;
    }


    // 할인하는 모든 가게 조회 // 할인전
    public Map<String, Object> getDCStoreList(Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> returnList = new ArrayList<ShowStoreListVO>();
       
        
        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAll()) {
            ShowStoreListVO vo = new ShowStoreListVO(data);
            if (data.getUnivTimeInfoEntity().getUtiSeq() == utiSeq) {
                //ShowStoreListVO vo = new ShowStoreListVO(data);
                if(vo.getdiscount() > 0) {
                    StoreImageEntity imgEntity = storeImageRepository.findBySimgSiSeqAndSimgDivision(data.getStoreInfoEntity().getSiSeq(),9);
                        String img = "";
                        if(imgEntity != null) {
                            img = imgEntity.getSimgUri();
                        }
                        vo.setSimgUriLogo(img);
                        if (reviewRepository.findAvgRiScoreBySiSeq(data.getStoreInfoEntity().getSiSeq())==null) {
                               vo.setScoreAvg(0.0);
                        }
                        else {
                            Double reviewAvg = (int) (reviewRepository
                                    .findAvgRiScoreBySiSeq(data.getStoreInfoEntity().getSiSeq()) * 10) / 10.0;
                            vo.setScoreAvg(reviewAvg);
                        }
                        vo.setReviewCount(reviewRepository.countBySiSeq(data.getStoreInfoEntity().getSiSeq()));
                    returnList.add(vo);
                }
            }
        } 
        resultMap.put("status", true);

        if (univTimeInfoRepository.countByUtiSeq(utiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 시간정보 입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } 
        else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(배달하는 가게가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } 
        else {
            resultMap.put("message", "할인가게 조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("timeName", univTimeInfoRepository.findByUtiSeq(utiSeq).getUtiName());
        resultMap.put("list", returnList);
        return resultMap;
    }


    //  할인율에 따라 가게 조회 정렬하기
    public Map<String, Object> getOrderByStoreList(Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> returnList = new ArrayList<ShowStoreListVO>();

        for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAllByStdUtiSeq(utiSeq)) {
            // List<StoreInfoEntity> list =  storeInfoRepository.findAllByOrderBySiDiscount();
            ShowStoreListVO vo = new ShowStoreListVO(data);
            returnList.add(vo);
            returnList = returnList.stream().sorted(Comparator.comparing(ShowStoreListVO::getdiscount).reversed()).collect(Collectors.toList());
        }

        resultMap.put("status", true);
        if (univTimeInfoRepository.countByUtiSeq(utiSeq) == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 시간정보 입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
            return resultMap;
        } else if (returnList.size() == 0) {
            resultMap.put("message", "조회 완료(배달하는 가게가 없습니다.)");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
        }
        resultMap.put("timeName", univTimeInfoRepository.findByUtiSeq(utiSeq).getUtiName());
        resultMap.put("list", returnList);
        return resultMap;
    }

    // 가게 상세정보 조회에 사용
    public Map<String, Object> getStoreInfo(Long siSeq, Long utiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (storeInfoRepository.findById(siSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 가게입니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        } else if (univTimeInfoRepository.findById(utiSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "해당하는 배달시간이 없습니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        } else if (storeTimeDetailRepository.findByStdSiSeqAndStdUtiSeq(siSeq, utiSeq) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "이 가게는 이 시간/장소에 배달하지 않습니다.");
            resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
        } else {
            ShowStoreInfoVO returnData = new ShowStoreInfoVO(storeDetailInfoRepository.findBySdiSeq(siSeq),
                    storeTimeDetailRepository.findByStdSiSeqAndStdUtiSeq(siSeq, utiSeq));
            List<ClosePickupTimeVO> timeList = new ArrayList<ClosePickupTimeVO>();

            StoreImageEntity imgEntity = storeImageRepository.findBySimgSiSeqAndSimgDivision(siSeq,1);
            String img = "";
            if(imgEntity != null) {
                img = imgEntity.getSimgUri();
            }
                returnData.setSimgUriCover(img);

            for (StoreTimeDetailEntity data : storeTimeDetailRepository.findAllByStdSiSeq(siSeq)) {
                if (data.getUnivTimeInfoEntity().getUnivInfoEntity() == univTimeInfoRepository.findByUtiSeq(utiSeq)
                        .getUnivInfoEntity()) {
                    ClosePickupTimeVO vo = new ClosePickupTimeVO(data);
                    if (data.getUnivTimeInfoEntity().getUtiSeq() == utiSeq) {
                        vo.setThisTime(true);
                    }
                    timeList.add(vo);
                }
            }
            returnData.setClosePickUpTimeList(timeList);
            resultMap.put("status", true);
            resultMap.put("message", "조회 완료");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("data", returnData);
        }
        return resultMap;
    }

    //localhost:8888/list/store/search?keyword=닭
    public Map<String, Object> searchStore(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowStoreListVO> resultList = new ArrayList<ShowStoreListVO>();
        if (keyword==null || keyword.equals("")) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            resultMap.put("message", "검색어를 입력하세요.");
            return resultMap;
        }
        // 1. keyword -> storeInfoEntity검색 -> List<StoreInfoEntity> -> List<Long(siSeq)>
        for (StoreInfoEntity data : storeInfoRepository.findAllBySiNameContaining(keyword)) {
            //resultList.add( new StoreInfoEntity(data));
            
            //resultList.add(new (Long)StoreInfoEntity(data));
            // resultList.add(new ShowStoreListVO(data));
        }
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("message", "성공");
        resultMap.put("list", resultList);
        return resultMap;
    }

    // 메뉴 옵션 조회
    public Map<String, Object> getMenuOptionList(Long fmiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ShowFoodOptionVO> menuOptionList = new ArrayList<ShowFoodOptionVO>();
        if (foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).size() == 0) {
            Optional<FoodMenuInfoEntity> entity = foodMenuInfoRepository.findById(fmiSeq);
            if (entity.isEmpty()) {
                resultMap.put("status", false);
                resultMap.put("message", "등록되지않은 메뉴번호 입니다.");
                resultMap.put("code", HttpStatus.NOT_ACCEPTABLE);
                return resultMap;
            } else {
                ShowMenuDetailVO vo = new ShowMenuDetailVO(entity.get());
                resultMap.put("status", true);
                resultMap.put("message", "옵션이 없는 메뉴");
                resultMap.put("code", HttpStatus.OK);
                resultMap.put("data", vo);
                return resultMap;
            }
        }
        ShowMenuDetailVO showMenuDetailVO = new ShowMenuDetailVO(
                foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq).get(0).getFoodMenuInfoEntity());

        for (FoodOptionConnectEntity data : foodOptionConnectRepository.findAllByFocFmiSeq(fmiSeq)) {
            List<ShowFoodDetailOptionVO> detailOptionList = new ArrayList<ShowFoodDetailOptionVO>();
            ShowFoodOptionVO vo = new ShowFoodOptionVO(data);

            for (FoodDetailOptionEntity data2 : data.getFoodMenuOptionEntity().getFdoEntityList()) {
                detailOptionList.add(new ShowFoodDetailOptionVO(data2));
            }

            vo.setDetailOptionList(detailOptionList);
            menuOptionList.add(vo);
        }
        showMenuDetailVO.setOptionList(menuOptionList);

        resultMap.put("status", true);
        resultMap.put("message", "조회 완료");
        resultMap.put("code", HttpStatus.OK);
        resultMap.put("data", showMenuDetailVO);
        return resultMap;
    }



    // public List<StoreClosedDayInfoVO> getClosedDayVOList(Long scdiSiSeq) {
    //     List<StoreClosedDayInfoVO> list = new ArrayList<StoreClosedDayInfoVO>();
    //     for (StoreClosedDayEntity data : storeClosedDayRepository.findAll()) {
    //         StoreClosedDayInfoVO closedDayVO = new StoreClosedDayInfoVO();
    //         if (scdiSiSeq == data.getStoreInfoEntity().getSiSeq()) {
    //             closedDayVO.setScdi_day(data.getScdiDay());
    //             closedDayVO.setScdi_day_no(data.getScdiDayNo());
    //         } else {
    //             continue;
    //         }
    //         list.add(closedDayVO);
    //     }
    //     return list;
    // }

    // public StoreInfoEntity closedDaystatus() {
    //     LocalDate now = LocalDate.now();
    //     DayOfWeek dayOfWeek = now.getDayOfWeek();
    //     int dayOfWeekNumber = dayOfWeek.getValue();
    //     // StoreInfoEntity entity = new StoreInfoEntity();
    //     List<StoreClosedDayEntity> list = new ArrayList<StoreClosedDayEntity>();
    //     for (StoreClosedDayEntity data : list) {
    //         if (data.getScdiDayNo() == dayOfWeekNumber) {
    //             StoreInfoEntity entity = storeInfoRepository.findBySiSeq(data.getStoreInfoEntity().getSiSeq());
              
    //         }
    //     }
    //     // return  entity.setSiStatus(3);

    // }

}

 

    

    



