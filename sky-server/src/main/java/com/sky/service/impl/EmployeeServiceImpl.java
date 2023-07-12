package com.sky.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import com.sky.exception.BusinessException;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public Employee login(EmployeeDTO employeeDTO) {
        String username = employeeDTO.getUsername();
        String password = employeeDTO.getPassword();

        Employee employee = employeeMapper.getByUsername(username);
        if (employee == null){
            throw new BusinessException(500,"账号不存在");
        }

        if(!SecureUtil.md5(password).equals(employee.getPassword())){

            throw new BusinessException(500,"密码错误");
        }
        if(Objects.equals(employee.getStatus(), StatusConstant.DISABLE)){
            throw new BusinessException(500,"账号已经被禁用");
        }
        return employee;
    }
}
