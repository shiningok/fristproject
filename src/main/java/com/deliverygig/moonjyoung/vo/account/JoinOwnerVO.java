package com.deliverygig.moonjyoung.vo.account;
import lombok.Data;

@Data
public class JoinOwnerVO {
    private String oiId;  
    private String oiPwd;
    private String oiNickName;
    private String oiEmail;
    private String oiPhone;  

//    public OwnerEntity createEntity(){
//      return OwnerEntity.builder()
//          .oiId(oiId)
//          .oiPwd(oiPwd)
//          .oiNickName(oiNickName)
//          .oiEmail(oiEmail)
//          .oiPhone(oiPhone)
//          .build();
//    }
}
