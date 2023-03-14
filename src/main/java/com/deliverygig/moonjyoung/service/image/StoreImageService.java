package com.deliverygig.moonjyoung.service.image;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;
import com.deliverygig.moonjyoung.repository.image.StoreImageRepository;

import jakarta.transaction.Transactional;

@Service
public class StoreImageService {
    @Autowired StoreImageRepository StRepo;

public Map<String, Object> addStoreImage (StoreImageEntity data) {
    Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
    StRepo.save(data);
    resultMap.put("status", true);
    resultMap.put("message", "파일이 저장되었습니다.");
    resultMap.put("code", HttpStatus.OK);
    return resultMap;
}
 @Transactional
    public Map<String, Object> deleteImage(@RequestBody StoreImageEntity data) {
      Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
      //PickUpAreaEntity puaidelete = (PickUpAreaEntity)session.getAttribute("puaidelete");
          // PiRepo.deleteByPuaiSeq(puaidelete.getPuaSeq());
        if(StRepo.countBySimgSeq(data.getSimgSeq()) == 1) {
          StRepo.deleteBySimgSeq(data.getSimgSeq());
          resultMap.put("status", true);
          resultMap.put("message", "삭제되었습니다.");
          resultMap.put("code", HttpStatus.OK);
          return resultMap;
        }
        resultMap.put("status", false);
        resultMap.put("message", "매장 Seq 오류입니다.");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
        return resultMap;
      }

        //  public Map<String, Object> getFilenameByUri(@RequestBody StoreImageEntity data) {
        //   Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        //     StRepo.findBySimgUri(data.getSimgUri()) {
        //     }
             
         //     return data.get(0).getSimgImage();
        
      public String getFilenameByUri(String uri){
        StoreImageEntity data = StRepo.findTopBySimgUri(uri);
        return data.getSimgImage();
      }
}
