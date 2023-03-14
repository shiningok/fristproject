package com.deliverygig.moonjyoung.service.image;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;
import com.deliverygig.moonjyoung.repository.image.FoodImageRepository;

import jakarta.transaction.Transactional;



@Service
public class FoodImageService {
    @Autowired FoodImageRepository FoRepo;
public Map<String, Object> addFoodImage(FoodImageEntity data) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    FoRepo.save(data);
    resultMap.put("status", true);
    resultMap.put("message", "파일이 저장되었습니다.");
    resultMap.put("code", HttpStatus.OK);
    return resultMap;
  }
   @Transactional
    public Map<String, Object> deleteImage(@RequestBody FoodImageEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //PickUpAreaEntity puaidelete = (PickUpAreaEntity)session.getAttribute("puaidelete");
           // PiRepo.deleteByPuaiSeq(puaidelete.getPuaSeq());
          if(FoRepo.countByFiSeq(data.getFiSeq()) == 1) {
            FoRepo.deleteByFiSeq(data.getFiSeq());
            resultMap.put("status", true);
            resultMap.put("message", "삭제되었습니다.");
            resultMap.put("code", HttpStatus.OK);
            return resultMap;
          }
          resultMap.put("status", false);
          resultMap.put("message", "메뉴이름 Seq 오류입니다.");
          resultMap.put("code", HttpStatus.BAD_REQUEST);
          return resultMap;
        }

       public String getFilenameByUri(String uri){
        FoodImageEntity data = FoRepo.findTopByFiUri(uri);
        return data.getFiFile();
      }
  }

        

