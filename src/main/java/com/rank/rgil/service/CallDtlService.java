package com.rank.rgil.service;

import com.rank.rgil.entities.CallDtl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CallDtlService extends JpaRepository<CallDtl, Long> {

    //CallDtl saveCallDtl(CallDtl callDtl);

    @Query(value="select * from call_dtl where call_mst_id=?1 and call_type_info <> 'Threeway Specialist'", nativeQuery = true)
    CallDtl findCurrentCallAgent(Long callMstId);

}
