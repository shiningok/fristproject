package com.deliverygig.moonjyoung.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    // 회원가입을 위한 것들
    public Integer countByOiId(String oiId);
    public Integer countByOiNickName(String oiNickName);
    public Integer countByOiEmail(String oiEmail);
    public Integer countByOiPhone(String oiPhone);
    // 로그인을 위한 것들 
    public OwnerEntity findByOiIdAndOiPwd(String oiId, String oiPwd);
    // 회원가입 새로운 시도 
    public OwnerEntity findByOiIdAndOiNickNameAndOiEmailAndOiPhone (String oiId, String oiNickName, String oiEmail,  String oiPhone );

    public OwnerEntity findByOiPwd(String oiPwd);

    public void deleteByOiSeq(Long oiSeq);   

    public Page<OwnerEntity> findAll(Pageable page);
    public OwnerEntity findByOiSeq(Long oiSeq);
    public Page<OwnerEntity> findByOiIdContains(String oiId, Pageable pageable);
}
