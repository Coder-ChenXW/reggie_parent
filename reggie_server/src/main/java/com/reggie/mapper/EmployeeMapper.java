package com.reggie.mapper;

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

}
