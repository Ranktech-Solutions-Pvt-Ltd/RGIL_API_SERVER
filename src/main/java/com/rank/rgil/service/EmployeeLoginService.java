package com.rank.rgil.service;

import com.rank.rgil.entities.EmployeeMst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeLoginService extends JpaRepository<EmployeeMst, Long> {

    @Query(value="select * from employee_mst where login_id=?1 and login_passwd=?2 and emp_typ_id=2 and delete_flg=false", nativeQuery = true)
    EmployeeMst checkLogin(String userLoginId, String userPassword, String sessionId);

}
