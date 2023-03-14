package com.deliverygig.moonjyoung.api;


import java.net.URLEncoder;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.image.FoodImageEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;
import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;
import com.deliverygig.moonjyoung.repository.image.FoodImageRepository;
import com.deliverygig.moonjyoung.repository.image.PickUpAreaImageRepository;
import com.deliverygig.moonjyoung.service.image.FoodImageService;
import com.deliverygig.moonjyoung.service.image.PickUpAreaImageService;
import com.deliverygig.moonjyoung.service.image.StoreImageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class FileController { 
  @Autowired PickUpAreaImageService pService;
  @Autowired StoreImageService sService;
  @Autowired FoodImageService fService;
  @Autowired PickUpAreaImageRepository pRepo;
  
  @Value ("${file.image.pickuparea}") String pickuparea_img_path;
  @Value("${file.image.store}") String store_img_path;
  @Value("${file.image.food}") String food_img_path;
  
  @PutMapping("/upload/{type}")
  public ResponseEntity<Object> putImageUpload(
      @PathVariable String type, @RequestPart MultipartFile file) 
  {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    System.out.println(file.getOriginalFilename());
    Path folderLocation = null;
    if (type.equals("pickuparea")) {
      folderLocation = Paths.get(pickuparea_img_path);
    }
    else if (type.equals("store")) {
      folderLocation = Paths.get(store_img_path);
    }
    else if (type.equals("food")) {
      folderLocation = Paths.get(food_img_path);
    }
    else {
      resultMap.put("status", false);
      resultMap.put("message", "타입정보가 잘못되었습니다.(ex : upload/pickuparea, upload/store, upload/food)");
      return new ResponseEntity<Object>(resultMap, HttpStatus.BAD_REQUEST);
    }
    
    String originFileName = file.getOriginalFilename();
    String[] split = originFileName.split("\\.");
    String ext = split[split.length - 1];
    String filename = "";
    for (int i = 0; i < split.length - 1; i++) {
      filename += split[i]; 
    }
    String saveFilename = type + "_"; 
    Calendar c = Calendar.getInstance();
    saveFilename += c.getTimeInMillis() + "." + ext; 


    
    Path targetFile = folderLocation.resolve(saveFilename); 
    try {
      Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING); 
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (type.equals("pickuparea")) {
        PickUpAreaImageEntity data = new PickUpAreaImageEntity();
      data.setPuaiImage(saveFilename);
      data.setPuaiUri(filename);
      // lService.addLocationImage(data);
      Map<String,Object> resultMap2 = pService.addPickUpAreaImage(data);
    
      return new ResponseEntity<Object>(resultMap2 , (HttpStatus)resultMap2.get("code"));
     
     // resultMap.put("message", "location 성공");
     
    }
    
    else if (type.equals("store")) {
        StoreImageEntity data = new StoreImageEntity();
        data.setSimgImage(saveFilename);
        data.setSimgUri(filename);
        //sService.addStoreImage(data);
        Map<String,Object> resultMap2 = sService.addStoreImage(data);
      return new ResponseEntity<Object>(resultMap2 , (HttpStatus)resultMap2.get("code"));
           
        //map.put("message", "store 성공");
    }
    else if (type.equals("food")) {
      FoodImageEntity data = new FoodImageEntity();
      data.setFiFile(saveFilename);
      data.setFiUri(filename);
      //fService.addFoodImage(data);
      Map<String,Object> resultMap2 = fService.addFoodImage(data);
      return new ResponseEntity<Object>(resultMap2 , (HttpStatus)resultMap2.get("code"));
      //map.put("message", "food 성공");
    }
    
    return new ResponseEntity<>(resultMap, HttpStatus.OK);
  }  


     @Transactional
   @PostMapping("/delete/pickuparea")
  public ResponseEntity<Object> PickUpAreaImageDelete(@RequestBody PickUpAreaImageEntity data ) {
   // pRepo.deleteByPuaiSeq(data);
    //Map<String,Object> resultMap = pService.deleteImage(seq)
    //return new ResponseEntity<>(HttpStatus.OK);
       Map<String, Object> resultMap = pService.deleteImage(data);
       
        return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
  }

      @Transactional
    @PostMapping("/delete/food")
  public ResponseEntity<Object> FoodImageDelete(@RequestBody FoodImageEntity data) {
  Map<String, Object> resultMap = fService.deleteImage(data);
  return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
}

      @Transactional
    @PostMapping("/delete/store")
  public ResponseEntity<Object> StoreImageDelete(@RequestBody StoreImageEntity data) {
  Map<String, Object> resultMap = sService.deleteImage(data);
  return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
}



//   //http://localhost:8080/api/product?prodNo=1 파람이니까 주소값 ! 
//  @DeleteMapping("/upload/{type}")
//   public ResponseEntity<Map<String, Object>> deleteImage( @RequestParam Long fiSeq ) { 
//     Map<String, Object> map = new LinkedHashMap<String, Object>();
//       for (FoodImageEntity f : food_list) {
//         if (f.getFiSeq() == fiSeq) {
//           food_list.remove(f);
//           map.put("message", "데이터가 변경되었습니다");
//           return new ResponseEntity<Map<String, Object>>(map, HttpStatus.ACCEPTED);
//         }
//       }
//        map.put("message", "잘못된 사진번호 :" + fiSeq);
//     return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);

@GetMapping("/download/{type}/{uri}")
  public ResponseEntity<Resource> getImage(@PathVariable String type, @PathVariable String uri,
      HttpServletRequest request) throws Exception {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    Path folderLocation = null;
    if (type.equals("pickuparea")) {
      folderLocation = Paths.get(pickuparea_img_path);
    }
    else if (type.equals("store")) {
      folderLocation = Paths.get(store_img_path);
    }
    else if (type.equals("food")) {
      folderLocation = Paths.get(food_img_path);
    }
    
    
    String filename = null;
    if (type.equals("pickuparea")) {
      filename = pService.getFilenameByUri(uri);
    }
    else if (type.equals("store")) {
      System.out.println("================================="+uri);
      filename = sService.getFilenameByUri(uri);
      System.out.println("================================="+filename);
    }
     else if (type.equals("food")) {
      filename = fService.getFilenameByUri(uri);
    }

    // .을 기준으로 나누어서
    String[] split = filename.split("\\.");
    // 마지막 배열을 가져옴 (= 확장자)
    String ext = split[split.length - 1];
    // 가져오는 파일의 이름 : 파일이름.확장자
    String exportName = uri + "." + ext;

    // 폴더 경로와 파일의 이름을 합쳐서 target 파일의 경로를 만듬
    Path targetFile = folderLocation.resolve(filename);
    // 다운로드 가능한 형태로 변환하기 위한 Resource객체 생성

    Resource r = null;
    try {
      // 일반파일 -> url로 첨부가능한 형태로 변환
      r = new UrlResource(targetFile.toUri());
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 첨부된 파일의 타입을 저장하기 위한 변수 생성
    String contentType = null;
    try {
      // 파일의 종류에 따라 (이미지, pdf 등) 처리를 달리 하기위해 파일의 종류를 가져와서 변수에 저장함
      contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
      // 산출한 파일의 타입이 null이라면
      if (contentType == null) { // 파일의 종류가 파악되지 않으면
        // 일반 파일로 처리 ,그냥 다운로드 처리함
        contentType = "application/octet-stream"; // octet-stream - 일반파일
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(). // 응답의 코드를 200 ok로 설정하고
        // 산출한 타입을 응답에 맞는 형태로 변환
        contentType(MediaType.parseMediaType(contentType)). // contentType 입력된 값을 MediaType으로 변환
        // 내보낼 내용의 타입을 설정 (파일),
        // attachment; filename*=\"" + r.getFilename() + "\" 요청한 쪽에서 다운로드 한 파일의 이름을 결정
        header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename*=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"").
        // 변환된 파일을 ResponseEntity에 추가, 실제로 우리가 파일을 받아가는 소스
        body(r);
  }
}

    
   
  

  

