package com.rank.rgil.service;

import com.rank.rgil.entities.EmployeeCallStatus;
import com.rank.rgil.entities.EmployeeMst;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCallStatusService extends JpaRepository<EmployeeCallStatus, Long> {

    //EmployeeCallStatus saveEmployeeCallStatus(EmployeeCallStatus employeeCallStatus);//chayan

    //public boolean deleteStatus(EmployeeCallStatus employeeCallStatus);//chayan

    List<EmployeeCallStatus> findByEmpId(EmployeeMst empId);//chayan

}
