package com.deliverygig.moonjyoung.service.image;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;
import com.deliverygig.moonjyoung.repository.image.PickUpAreaImageRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class PickUpAreaImageService {
    @Autowired PickUpAreaImageRepository PiRepo;
    
    

public Map<String, Object> addPickUpAreaImage(PickUpAreaImageEntity data) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    PiRepo.save(data);
    resultMap.put("status", true);
    resultMap.put("message", "파일이 저장되었습니다.");
    resultMap.put("code", HttpStatus.OK);
    return resultMap;
  }
  @Transactional
    public Map<String, Object> deleteImage(@RequestBody PickUpAreaImageEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //PickUpAreaEntity puaidelete = (PickUpAreaEntity)session.getAttribute("puaidelete");
           // PiRepo.deleteByPuaiSeq(puaidelete.getPuaSeq());
          if(PiRepo.countByPuaiSeq(data.getPuaiSeq()) == 1) {
            PiRepo.deleteByPuaiSeq(data.getPuaiSeq());
            resultMap.put("status", true);
            resultMap.put("message", "삭제되었습니다.");
            resultMap.put("code", HttpStatus.OK);
            return resultMap;
          }
          resultMap.put("status", false);
          resultMap.put("message", "수령장소 Seq 오류입니다.");
          resultMap.put("code", HttpStatus.BAD_REQUEST);
          return resultMap;
        }
        public String getFilenameByUri(String uri){
      PickUpAreaImageEntity data = PiRepo.findTopByPuaiUri(uri);
      return data.getPuaiImage();
  }
}


        