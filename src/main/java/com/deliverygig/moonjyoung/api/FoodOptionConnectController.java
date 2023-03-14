// package com.deliverygig.moonjyoung.api;

// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.deliverygig.moonjyoung.repository.food.FoodOptionConnectRepository;
// import com.deliverygig.moonjyoung.service.FoodOptionConnectService;
// import com.deliverygig.moonjyoung.vo.optionconnect.AddConnectVO;

// @RestController
// @RequestMapping("/foodOption")
// public class FoodOptionConnectController {
//     @Autowired FoodOptionConnectService focService;
//     @Autowired FoodOptionConnectRepository focRepo;

//     // create
//     @PostMapping("/insert")
//     public ResponseEntity<Object> ConnectInsert(@RequestBody AddConnectVO data) {
//         Map<String, Object> resultMap = focService.addConnect(data);
//         return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));

//     }
//     // read
//     // update
//     // delete


// }
