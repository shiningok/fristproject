package com.deliverygig.moonjyoung.api.adminpage.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deliverygig.moonjyoung.entity.account.MasterEntity;
import com.deliverygig.moonjyoung.repository.account.MasterRepository;
import com.deliverygig.moonjyoung.vo.account.MasterAddVO;
import com.deliverygig.moonjyoung.vo.account.MasterLoginInfoVO;
import com.deliverygig.moonjyoung.vo.account.MasterLoginVO;

import jakarta.transaction.Transactional;

@Service
public class MasterListService {
  @Autowired MasterRepository mRepository;

  public Map<String, Object> getMasterList(String keyword, Pageable pageable) {
    Page<MasterEntity> page = mRepository.findByMiIdContains(keyword, pageable);
    List<MasterEntity> list = new ArrayList<MasterEntity>();
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    for(MasterEntity a : page.getContent()) {
      if(a.getMiGrade() == 2) {
        list.add(a);
      }
    }
    map.put("list", list);
    map.put("total", page.getTotalElements());
    map.put("totalpage", page.getTotalPages());
    map.put("currentPage", page.getNumber());
    return map;
  }

  public Map<String, Object> postMasterLogin(MasterLoginInfoVO login) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    MasterEntity loginUser = mRepository.findByMiIdAndMiPwd(login.getMaster_id(), login.getMaster_pwd());
    if(loginUser == null) {
      map.put("loginStatus", "failed");
      map.put("message", "아이디 또는 비밀번호 오류입니다.");
    }
    else {
      map.put("loginStatus", true);
      map.put("message", "정상 로그인 되었습니다.");
      map.put("loginUser", new MasterLoginVO(loginUser));
    }
    return map;
    }
    
    public Map<String, Object> postAddAdmin(MasterAddVO data) {
      Map<String, Object> map = new LinkedHashMap<String, Object>();
      if(data.getId() == null || data.getId().equals("")) {
        map.put("status", false);
        map.put("message","아이디를 입력하세요.");
      }
      else if(mRepository.countByMiId(data.getId()) !=0) {
        map.put("status", false);
        map.put("message", data.getId()+"은/는 이미 사용중입니다.");
      }
      else if(data.getPwd() == null ||data.getPwd().equals("")){
        map.put("status",false);
        map.put("message","비밀번호를 입력하세요.");
      }
      else if (data.getNickName() == null || data.getNickName().equals("")) {
        map.put("status", false);
        map.put("message", "이름을 입력하세요");
      }
      else {
        MasterEntity entity = MasterEntity.builder()
        .miId(data.getId()).miPwd(data.getPwd()).miNickName(data.getNickName())
        .miGrade(2).miStatus(2).build();
        mRepository.save(entity);
        map.put("status",true);
        map.put("message", "관리자 계정 등록 신청 완료");
      }
      return map;
    }

  @Transactional
  public void deleteMaster(Long miSeq) {
    mRepository.deleteById(miSeq);
  }
}
