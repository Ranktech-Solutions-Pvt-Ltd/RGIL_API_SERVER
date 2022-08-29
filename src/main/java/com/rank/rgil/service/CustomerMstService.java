package com.rank.rgil.service;

import com.rank.rgil.entities.CustomerMst;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMstService extends JpaRepository<CustomerMst, Long> {

    //CustomerMst saveCustomerMst(CustomerMst customerMst, EmployeeMst employeeMst);

    List<CustomerMst> findByCustId(String custId);
 
}
