package com.reggie.service;

import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import com.reggie.result.PageResult;

public interface EmployeeService {

    // 员工登录
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    // 新增员工
    void save(EmployeeDTO employeeDTO);

    // 分页查询
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    // 启用禁用员工账户
    void startOrStop(Integer status, Long id);

    // 根据id查询员工
    Employee getById(Long id);

    // 修改员工信息
    void update(EmployeeDTO employeeDTO);

}
