package com.rank.rgil.service;

import com.rank.rgil.entities.EmployeeMst;
import com.rank.rgil.entities.TenancyEmployeeMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TenancyEmployeeMapService extends JpaRepository<TenancyEmployeeMap, Long> {

    List<TenancyEmployeeMap> findByEmpId(EmployeeMst empid);//chayan

    //TenancyEmployeeMap saveTenancyEmployeeMap(TenancyEmployeeMap tenancyEmployeeMap);//chayan

    //void deleteTenancyEmployeeMap(TenancyEmployeeMap tenancyEmployeeMap);//chayan

}
