package com.deliverygig.moonjyoung.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.account.MasterEntity;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Long> {
    public Page<MasterEntity> findAll(Pageable page);
    public Integer countByMiId(String miId);
    public MasterEntity findByMiIdAndMiPwd(String miId, String miPwd);
    public MasterEntity findByMiSeq(Long miSeq);
    public Page<MasterEntity> findByMiIdContains(String miId, Pageable pageable);
}
