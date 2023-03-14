package com.deliverygig.moonjyoung.repository.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deliverygig.moonjyoung.entity.review.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    // 1구매내역 = 1리뷰 체크
    @Query(value = "select count(*) from review_info where ri_bmoc_seq = :riBmocSeq", nativeQuery = true)
    public Integer countByRiBmocSeq(@Param("riBmocSeq") Long riBmocSeq);
    // 가게 - 총 리뷰갯수
    @Query(value = "select count(*) from review_info a left join basket_menu_options_combine b on a.ri_bmoc_seq=b.bmoc_seq left join store_time_detail c on b.bmoc_std_seq=c.std_seq left join store_info d on c.std_si_seq=d.si_seq where d.si_seq = :siSeq", nativeQuery = true)
    public Integer countBySiSeq(@Param("siSeq") Long siSeq);
    // 가게 - 평균 평점
    @Query(value = "select avg(ri_score) from review_info a left join basket_menu_options_combine b on a.ri_bmoc_seq=b.bmoc_seq left join store_time_detail c on b.bmoc_std_seq=c.std_seq left join store_info d on c.std_si_seq=d.si_seq where d.si_seq = :siSeq", nativeQuery = true)
    public Double findAvgRiScoreBySiSeq(@Param("siSeq") Long siSeq);

    public Page<ReviewEntity> findByriContentsContains(String riContents, Pageable pageable);
    public ReviewEntity findByRiSeq(Long riSeq);

    // 가게 리뷰 조회
    @Query(value = "select * from review_info a left join basket_menu_options_combine b on a.ri_bmoc_seq=b.bmoc_seq left join store_time_detail c on b.bmoc_std_seq=c.std_seq left join store_info d on c.std_si_seq=d.si_seq where d.si_seq = :siSeq and a.ri_status = :riStatus order by a.ri_reg_dt desc", nativeQuery = true)
    public List<ReviewEntity> findAllBySiSeqAndRiStatus(@Param("siSeq") Long siSeq, @Param("riStatus") Integer riStatus);
    // 내 리뷰 조회
    @Query(value = "select * from review_info a left join basket_menu_options_combine b on a.ri_bmoc_seq=b.bmoc_seq left join basket_info c on b.bmoc_bi_seq=c.bi_seq where c.bi_ci_seq = :ciSeq order by a.ri_reg_dt desc", nativeQuery = true)
    public List<ReviewEntity> findAllByCiSeq(@Param("ciSeq") Long ciSeq);
}
