package com.rank.rgil.service;

import com.rank.rgil.entities.ReasonMst;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReasonMstService extends JpaRepository<ReasonMst, Long> {

    @Query(value="select * from reason_mst where type=?1 and active_flg=true and delete_flg=false and reason_cd <> 'NRDFT' order by reason_cd desc", nativeQuery = true)
    List<ReasonMst> findAllActivenNonDeletedReasonMsts(String reasonType);//chayan

    List<ReasonMst> findByReasonCd(String reasonCd);//chayan

    List<ReasonMst> findByType(String Type);//chayan

}
