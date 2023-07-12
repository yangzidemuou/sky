package com.sky.service;

import com.sky.dto.EmployeeDTO;

import com.sky.entity.Employee;

public interface EmployeeService {

    Employee login(EmployeeDTO employeeDTO);

    /**
     * 新增员工
     */
    void save(EmployeeDTO employeeDTO);
}

