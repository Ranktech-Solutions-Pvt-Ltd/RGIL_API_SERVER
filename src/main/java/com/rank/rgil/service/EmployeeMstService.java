package com.rank.rgil.service;

import com.rank.rgil.entities.EmployeeMst;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeMstService extends JpaRepository<EmployeeMst, Long> {

    //EmployeeMst saveEmployeeMst(EmployeeMst employeemst, EmployeeMst loginEmployeeMst);//chayan

    List<EmployeeMst> findByLoginId(String loginId);//chayan

}
