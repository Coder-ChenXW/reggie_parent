package com.reggie.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.reggie.constant.MessageConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.context.BaseContext;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import com.reggie.exception.AccountLockedException;
import com.reggie.exception.AccountNotFoundException;
import com.reggie.exception.PasswordErrorException;
import com.reggie.mapper.EmployeeMapper;
import com.reggie.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author: ChenXW
 * @Date:2024/2/19 14:26
 * @Description:
 **/

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {

        // RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // System.out.println(requestAttributes);

        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();


        Employee employee = employeeMapper.getByUserName(username);

        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!md5DigestAsHex.equals(employee.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            // 账户被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }


        return employee;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        // 属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // TODO 后续解决当前问题
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);

    }

}
