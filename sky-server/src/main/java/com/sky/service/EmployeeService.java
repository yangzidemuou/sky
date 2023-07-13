package com.sky.service;

import com.sky.dto.EmployeeDTO;

import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    Employee login(EmployeeDTO employeeDTO);

    /**
     * 新增员工
     */
    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}

