package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {

    /**
     * @description: 根据用户名查询员工
     * @author: ChenXW
     * @date: 2024/2/19 14:29
     */
    Employee getByUserName(String username);

    // 插入数据
    void insert(Employee employee);

    // 分页查询
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void updateStatusById(Integer status, Long id);

    // 根据主键id查询员工
    Employee getById(Long id);

    // 根据id修改员工信息
    void update(Employee employee);

}
