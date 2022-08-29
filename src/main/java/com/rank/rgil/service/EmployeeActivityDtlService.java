package com.rank.rgil.service;

//import com.rank.rgil.dto.AgentStatusDto;
import com.rank.rgil.entities.EmployeeActivityDtl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeActivityDtlService extends JpaRepository<EmployeeActivityDtl, Long> {

    //EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl, EmployeeMst employeeMst);//chayan

    @Query(value="select * from employee_activity_dtl where emp_id=?1 and end_time is null and activity=?2 order by id desc", nativeQuery = true)
    List<EmployeeActivityDtl> findLastNonEndedActivityByType(Long empPkId, String activity);//chayan

    //EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl);//chayan

    @Query(value="select max(id) from employee_activity_dtl where emp_id=?1", nativeQuery = true)
    Long findMaxIdByEmpId(Long empId);//chayan

    //EmployeeActivityDtl findByActivityId(Long id);//chayan

    //String clearActivity();//chayan

}
