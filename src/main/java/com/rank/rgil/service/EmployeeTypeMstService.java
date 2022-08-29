package com.rank.rgil.service;

import com.rank.rgil.entities.EmployeeTypeMst;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTypeMstService extends JpaRepository<EmployeeTypeMst, Long> {

    List<EmployeeTypeMst> findByTypeName(String typeName);//chayan

}
