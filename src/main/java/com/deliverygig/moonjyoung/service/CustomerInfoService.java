package com.deliverygig.moonjyoung.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
import com.deliverygig.moonjyoung.repository.account.CustomerRepository;
import com.deliverygig.moonjyoung.utilities.AESAlgorithm;
import com.deliverygig.moonjyoung.vo.account.CustomerAddVO;
import com.deliverygig.moonjyoung.vo.account.LoginUserVO;
import com.deliverygig.moonjyoung.vo.account.LoginVO;
import com.deliverygig.moonjyoung.vo.account.UpdateCustomerInfoVO;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerInfoService {
    @Autowired
    CustomerRepository cRepo;
    // static boolean isStringEmpty(String str) {
    //     return str == null || str.isEmpty();
    // }

    // 회원가입
    public Map<String, Object> addMember(CustomerAddVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Boolean status = true;
        String replaceid = data.getCiId().replaceAll(" ", "");
        String replacepwd = data.getCiPwd().replaceAll(" ", "");
        String replacename = data.getCiName().replaceAll(" ", "");
        String replaceemail = data.getCiEmail().replaceAll(" ", "");
        String replacenickname = data.getCiNickName().replaceAll(" ", "");
        String replacephone = data.getCiPhone().replaceAll(" ", "");
        String replaceBirthday = data.getCiBirthday().replace(" ", "");

        if (replaceid.length() == 0) {
            status = false;
            resultMap.put("message", "아이디 입력창이 공백입니다. 확인해주세요.");
        } else if (replacepwd.length() == 0) {
            status = false;
            resultMap.put("message", "비밀번호 입력창이 공백입니다. 확인해주세요.");
        } else if (replacename.length() == 0) {
            status = false;
            resultMap.put("message", "이름 입력창이 공백입니다. 확인해주세요.");
        }
        else if (replacenickname.length() == 0) {
            status = false;
            resultMap.put("message", "닉네임 입력창이 공백입니다. 확인해주세요.");
        } else if (replaceemail.length() == 0) {
            status = false;
            resultMap.put("message", "이메일 입력창이 공백입니다. 확인해주세요.");
        } else if (replacephone.length() == 0) {
            status = false;
            resultMap.put("message", "폰 번호 입력창이 공백입니다. 확인해주세요.");
        } else if (replaceBirthday.length() == 0) {
            status = false;
            resultMap.put("message", "생년월일 입력창이 공백입니다. 확인해주세요.");
        }
        if (!status) {
            resultMap.put("status", false);
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }

        // if (replaceid.length() == 0 || replacepwd.length() == 0 || replacename.length() == 0
        //         || replaceemail.length() == 0 || replacenickname.length() == 0 || replacephone.length() == 0) {
        //     status = false;
        // }
        // if (!status) {
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     resultMap.put("message", "입력이 하지않는 부분이 있습니다. 확인해주세요.");
        //     return resultMap;
        // }

        if (cRepo.countByCiId(replaceid) == 1) {
            resultMap.put("message", replaceid + " 은/는 이미 등록된 아이디입니다.");
            status = false;
        } else if (cRepo.countByCiNickName(replacenickname) == 1) {
            resultMap.put("message", replacenickname + " 은/는 이미 등록된 닉네임입니다.");
            status = false;
        } else if (cRepo.countByCiEmail(replaceemail) == 1) {
            resultMap.put("message", replaceemail + " 은/는 이미 등록된 이메일입니다.");
            status = false;
        } else if (cRepo.countByCiPhone(replacephone) == 1) {
            resultMap.put("message", replacephone + " 은/는 이미 등록된 전화번호입니다.");
            status = false;
        }
        
        if (!status) {
            resultMap.put("status", "false");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }

        String nickname_pattern = "^[가-힣]*$";
        String eng_pattern = "^[a-zA-Z0-9]*$";
        String pwd_pattern = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{6,20}$";
        String email_pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        String phone_pattern = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
        String birth_pattern = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
        String name_pattern = "^[a-zA-Zㄱ-ㅎ가-힣]*$";
        Pattern a = Pattern.compile(eng_pattern);
        Pattern b = Pattern.compile(email_pattern);
        Pattern c = Pattern.compile(phone_pattern);
        Pattern d = Pattern.compile(pwd_pattern);
        Pattern k = Pattern.compile(nickname_pattern);
        Pattern t = Pattern.compile(birth_pattern);
        Pattern n = Pattern.compile(name_pattern);
        if (!a.matcher(replaceid).matches()) {
            status = false;
            resultMap.put("message", "아이디는 영문자와 숫자만 입력해주세요.");
        }

        else if (!d.matcher(replacepwd).matches()) {
            resultMap.put("message", "비밀번호는 영문자,특수문자,숫자 포함으로 입력해주세요.(6자이상)");
            status = false;
        }
        else if (!data.getCiCheckPwd().equals(replacepwd)) {
                resultMap.put("message", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                status = false;
        }
        else if (!n.matcher(replacename).matches()) {
            resultMap.put("message", "올바른 이름으로 입력해주세요.");
            status = false;
        } else if (!k.matcher(replacenickname).matches()) {
            resultMap.put("message", "닉네임은 한글로만 입력해주세요.");
            status = false;
        } else if (!b.matcher(replaceemail).matches()) {
            resultMap.put("message", "잘못된 이메일 입력방식입니다.");
            status = false;
        }

        else if (!c.matcher(replacephone).matches()) {
            resultMap.put("message", "잘못된 폰번호 입력방식입니다.");
            status = false;
        } else if (!t.matcher(replaceBirthday).matches()) {
            resultMap.put("message", "올바른 생년월일을 입력해주세요.");
            status = false;
        }

        if (!status) {
            resultMap.put("status", "false");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }

        data.setCiName(replaceid);
        data.setCiName(replacename);
        data.setCiNickName(replacenickname);
        data.setCiEmail(replaceemail);
        data.setCiPhone(replacephone);
        data.setCiBirthday(replaceBirthday);
        try {
            String encPwd = AESAlgorithm.Encrypt(replacepwd);
            data.setCiPwd(encPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDate date = LocalDate.parse(replaceBirthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        CustomerInfoEntity entity = new CustomerInfoEntity(data);
        entity.setCiBirthday(date);
        cRepo.save(entity);
        resultMap.put("status", true);
        resultMap.put("message", "회원가입이 완료되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
        // } 
        // else {
        //      resultMap.put("status", false);
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }
    }

    // 각 항목마다의 사용가능여부 체크 (회원가입)

    public Map<String, Object> AvailableMember(CustomerAddVO data, String type) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if (type.equals("id")) {
            String replaceid = data.getCiId().replaceAll(" ", "");
            String eng_pattern = "^[a-zA-Z0-9]*$";
            Pattern a = Pattern.compile(eng_pattern);
            if (replaceid.length() == 0 || !a.matcher(replaceid).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "아이디를 다시 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } else if (cRepo.countByCiId(replaceid) == 1) {
                resultMap.put("status", "false");
                resultMap.put("message", replaceid + " 은/는 이미 등록된 아이디입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            resultMap.put("status", "true");
            resultMap.put("message", replaceid + " 은/는 사용가능한 아이디입니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        if (type.equals("nickName")) {
            String nickname_pattern = "^[가-힣]*$";
            String replacenickname = data.getCiNickName().replaceAll(" ", "");
            Pattern k = Pattern.compile(nickname_pattern);
            if (replacenickname.length() == 0 || !k.matcher(replacenickname).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "닉네임을 다시 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } else if (cRepo.countByCiNickName(replacenickname) == 1) {
                resultMap.put("status", "false");
                resultMap.put("message", replacenickname + " 은/는 이미 등록된 닉네임입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            resultMap.put("status", "true");
            resultMap.put("message", replacenickname + " 은/는 사용가능한 닉네임입니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        if (type.equals("phone")) {
            String phone_pattern = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
            String replacephone = data.getCiPhone().replaceAll(" ", "");
            Pattern c = Pattern.compile(phone_pattern);
            if (replacephone.length() == 0 || !c.matcher(replacephone).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "폰번호를 다시 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } else if (cRepo.countByCiPhone(replacephone) == 1) {
                resultMap.put("status", "false");
                resultMap.put("message", replacephone + " 은/는 이미 등록된 번호입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            resultMap.put("status", "true");
            resultMap.put("message", replacephone + "은/는 사용가능한 번호입니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        if (type.equals("email")) {
            String email_pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
            String replaceemail = data.getCiEmail().replaceAll(" ", "");
            Pattern b = Pattern.compile(email_pattern);
            if (replaceemail.length() == 0 || !b.matcher(replaceemail).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "이메일을 다시 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } else if (cRepo.countByCiEmail(replaceemail) == 1) {
                resultMap.put("status", "false");
                resultMap.put("message", replaceemail + " 은/는 이미 등록된 이메일입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            resultMap.put("status", "true");
            resultMap.put("message", replaceemail + "은/는 사용가능한 이메일입니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        return resultMap;
    }

    // 로그인
    public Map<String, Object> LoginMember(LoginVO data, HttpSession session) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String encPwd = AESAlgorithm.Encrypt(data.getCiPwd());
        CustomerInfoEntity loginUser = cRepo.findByCiIdAndCiPwd(data.getCiId(), encPwd);

        if (loginUser == null) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 또는 비밀번호 오류입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }

        // if (loginUser.getStatus() == 2) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "존재하지 않는 아이디입니다.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }

        session.setAttribute("loginUser", new LoginUserVO(loginUser));
        resultMap.put("status", true);
        resultMap.put("message", "로그인에 성공하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("loginUser", loginUser);
        return resultMap;
    }

    // 로그아웃
    public Map<String, Object> LogOutMember(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (session.getAttribute("loginUser") == null) {
            resultMap.put("status", false);
            resultMap.put("message", "회원정보가 없습니다. 로그인 먼저해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        session.invalidate();
        resultMap.put("status", true);
        resultMap.put("message", "로그아웃되었습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }

    // 탈퇴
    public Map<String, Object> DeleteMember(/*LoginUserVO data2,*/ Long ciSeq, HttpSession session) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // LoginUserVO data = (LoginUserVO) session.getAttribute("loginUser");
        // if (session.getAttribute("loginUser") == null) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "회원정보가 없습니다. 로그인 먼저해주세요.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }
        // String encPwd = AESAlgorithm.Encrypt(data2.getCiPwd());
        // if (!data.getCiPwd().equals(encPwd)) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "잘못된 비밀번호입니다.");
        //     resultMap.put("code", HttpStatus.BAD_REQUEST);
        //     return resultMap;
        // }
        if (cRepo.findByCiSeq(ciSeq) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "알맞지 않은 회원번호입니다..");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;  
        }
        CustomerInfoEntity entity = cRepo.findById(ciSeq).get();
        cRepo.delete(entity);
        session.invalidate();
        resultMap.put("status", true);
        resultMap.put("message", "탈퇴되었습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }

    // 회원 조회 
    public Map<String, Object> CountMember(Long data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        data = cRepo.count();
        resultMap.put("total_member_num", data);
        resultMap.put("status", true);
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }

    // 회원수정
    public Map<String, Object> UpdateMember(UpdateCustomerInfoVO data2, String type, Long ciSeq) throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(cRepo.countByCiSeq(ciSeq) == 0){
            resultMap.put("status", false);
            resultMap.put("message", "잘못된 회원번호입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        CustomerInfoEntity entity =  cRepo.findById(ciSeq).get();
        if (type.equals("phone")) {
            String phone_pattern = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
            String replacephone = data2.getCiPhone().replaceAll(" ", "");
            Pattern c = Pattern.compile(phone_pattern);
            if (replacephone.length() == 0) {
                resultMap.put("status", "false");
                resultMap.put("message", "공백이 있습니다. 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (replacephone.equals(entity.getCiPhone())) {
                resultMap.put("message", "기존 폰번호로 변경할 수 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
             else if (cRepo.countByCiPhone(replacephone) == 1) {
                resultMap.put("message", replacephone + " 은/는 이미 등록된 전화번호입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (!c.matcher(replacephone).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "올바르지 않은 폰 번호입니다. (01x-xxxx-xxxx)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;  
            }
            data2.setCiPhone(replacephone);
            entity.setCiPhone(data2.getCiPhone());
            cRepo.save(entity);
            resultMap.put("status", "true");
            resultMap.put("message","변경되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }

        if (type.equals("email")) {
            String email_pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
            String replaceemail = data2.getCiEmail().replaceAll(" ", "");
            Pattern b = Pattern.compile(email_pattern);
            
            if (replaceemail.length() == 0) {
                resultMap.put("status", "false");
                resultMap.put("message", "공백이 있습니다. 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (replaceemail.equals(entity.getCiEmail())) {
                resultMap.put("message", "기존 이메일로 변경할 수 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (cRepo.countByCiEmail(replaceemail) == 1) {
                    resultMap.put("message", replaceemail + " 은/는 이미 등록된 이메일입니다.");
                    resultMap.put("code", HttpStatus.BAD_REQUEST);
                    return resultMap;
            }
            else if (!b.matcher(replaceemail).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "올바르지 않은 이메일 형식입니다. (******@********)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            data2.setCiEmail(replaceemail);
            entity.setCiEmail(data2.getCiEmail());
            cRepo.save(entity);
            resultMap.put("status", "true");
            resultMap.put("message","변경되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }

        if (type.equals("nickName")) {
            String nickname_pattern = "^[가-힣]*$";
            String replacenickname = data2.getCiNickName().replaceAll(" ", "");
            Pattern k = Pattern.compile(nickname_pattern);
            if (replacenickname.length() == 0) {
                resultMap.put("status", "false");
                resultMap.put("message", "공백이 있습니다. 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (replacenickname.equals(entity.getCiNickName())) {
                resultMap.put("status", "false");
                resultMap.put("message", "기존 닉네임으로 변경할 수 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (cRepo.countByCiNickName(replacenickname) == 1) {
                resultMap.put("status", "false");
                resultMap.put("message", replacenickname + " 은/는 이미 등록된 닉네임입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (!k.matcher(replacenickname).matches()) {
                resultMap.put("status", "false");
                resultMap.put("message", "올바르지 않은 닉네임 형식입니다. (한글)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            data2.setCiNickName(replacenickname);
            entity.setCiNickName(data2.getCiNickName());
            cRepo.save(entity);
            resultMap.put("status", "true");
            resultMap.put("message","변경되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }

        if (type.equals("pwd")) {
            String pwd_pattern = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{6,20}$";
            String replacepwd = data2.getCiUpdatePwd().replaceAll(" ", "");
            Pattern d = Pattern.compile(pwd_pattern);
            String decPwd = AESAlgorithm.Decrypt(entity.getCiPwd());
            if (!data2.getCiPwd().equals(decPwd)) {
                resultMap.put("message", "기존 비밀번호가 틀렸습니다. 다시 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } 
            else if (!d.matcher(replacepwd).matches()) {
                resultMap.put("message", "올바르지 않은 비밀번호 형식입니다. (영어 대소문자, 숫자, 특수문자(6자이상)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (replacepwd.equals(decPwd)) {
                resultMap.put("message", "기존 비밀번호로 변경할 수 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } else if (replacepwd.length() == 0) {
                resultMap.put("message", "공백이 있습니다. 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            else if (!data2.getCiCheckUpdatePwd().equals(replacepwd)) {
                resultMap.put("message", "새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }

            try {
                String encPwd = AESAlgorithm.Encrypt(replacepwd);
                data2.setCiPwd(encPwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            entity.setCiPwd(data2.getCiPwd());
            cRepo.save(entity);
            resultMap.put("status", "true");
            resultMap.put("message", "변경되었습니다");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        
        if (type.equals("name")) {
            String name_pattern = "^[a-zA-Zㄱ-ㅎ가-힣]*$";
            String replacename = data2.getCiName().replaceAll(" ", "");
            Pattern n = Pattern.compile(name_pattern);
            if (replacename.length() == 0) {
                resultMap.put("message", "공백이 있습니다. 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } 
            else if (replacename.equals(entity.getCiName())) {
                resultMap.put("message", "기존 이름으로 변경할 수 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            } 
            else if (!n.matcher(replacename).matches()) {
                resultMap.put("message", "올바른 이름으로 입력해주세요. (한글로만)");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            data2.setCiName(replacename);
            entity.setCiName(data2.getCiName());
            cRepo.save(entity);
            resultMap.put("status", "true");
            resultMap.put("message", "변경되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        return resultMap;
    }
}
