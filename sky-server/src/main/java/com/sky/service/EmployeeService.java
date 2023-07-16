package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import javax.print.attribute.IntegerSyntax;

public interface EmployeeService {
    Employee login(EmployeeDTO employeeDTO);
    /**
     * 新增员工
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void StatusChange(Integer status, Long id);
}

