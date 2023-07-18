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

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工状态
     * @param status
     * @param id
     */

    void StatusChange(Integer status, Long id);

    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    Employee SelectEmployeeById(Long id);

    /**
     * 更新编辑员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 修改员工密码
     * @param employeeDTO
     */
    void updatePassword(EmployeeDTO employeeDTO);
}

