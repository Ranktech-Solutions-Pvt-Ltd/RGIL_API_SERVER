package com.rank.rgil.service;

import com.rank.rgil.entities.CallMst;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallMstService extends JpaRepository<CallMst, Long> {

    //CallMst saveCallMst(CallMst callMst);

    List<CallMst> findByEmpId(Long empId);

}
