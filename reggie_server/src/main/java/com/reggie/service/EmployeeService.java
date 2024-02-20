package com.reggie.service;

import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;

public interface EmployeeService {

    // 员工登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    // 新增员工
    void save(EmployeeDTO employeeDTO);

}
