package com.deliverygig.moonjyoung.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;

public interface CustomerRepository extends JpaRepository<CustomerInfoEntity, Long> {
    public Integer countByCiEmail(String ciEmail);
    public Integer countByCiPhone(String ciPhone);
    public Integer countByCiId(String ciId);
    public Integer countByCiNickName(String ciNickName);
    public CustomerInfoEntity findByCiIdAndCiPwd(String ciId, String ciPwd);
    public Page<CustomerInfoEntity> findAll(Pageable page);
    public CustomerInfoEntity findByCiSeq(Long ciSeq);
    public Integer countByCiSeq(Long ciSeq);
    public Page<CustomerInfoEntity> findByCiIdContains(String ciId, Pageable pageable);
}
