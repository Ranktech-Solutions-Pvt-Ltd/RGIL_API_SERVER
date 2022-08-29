package com.rank.rgil.service;

import com.rank.rgil.entities.CallMst;
import com.rank.rgil.entities.CallRecords;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRecordsService extends JpaRepository<CallRecords, Long> {

    //public CallRecords saveCallRecord(CallRecords callRecord);

    List<CallRecords> findByCallId(CallMst callId);

}
