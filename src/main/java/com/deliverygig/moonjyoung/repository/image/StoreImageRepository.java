package com.deliverygig.moonjyoung.repository.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.image.StoreImageEntity;

    
@Repository
public interface StoreImageRepository extends JpaRepository<StoreImageEntity, Long> {
  //public List<StoreImageEntity> findBysimgUri(String simgUri);
   public void deleteBySimgSeq (Long simgSeq);
    public Integer countBySimgSeq(Long simgSeq );

    public StoreImageEntity findTopBySimgUri(String simgUri);

    // @Query(value = "select * from store_info_image where simg_si_seq =:simg_si_seq", nativeQuery = true)
    // public StoreImageEntity findBySimgSiSeq(@Param("simg_si_seq") Long simgSiSeq);

    @Query(value = "select * from store_image where simg_si_seq = :simgSiSeq and simg_division = :simgDivision", nativeQuery = true)
    public StoreImageEntity findBySimgSiSeqAndSimgDivision(@Param("simgSiSeq") Long simgSiSeq, @Param("simgDivision") Integer simgDivision);

   // public List<StoreImageEntity> i
  
}
