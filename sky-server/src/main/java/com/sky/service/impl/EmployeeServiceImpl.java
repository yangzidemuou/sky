package com.sky.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.ThreadLocalUtil;
import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import com.sky.exception.BusinessException;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.time.LocalDateTime;
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


    /**
     * 新增员工
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        //设置账号的状态
        employee.setStatus(StatusConstant.ENABLE);

        //新增员工设置默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置创建时间，修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置当前记录的创建人id 和修改人id
        employee.setUpdateUser(ThreadLocalUtil.getCurrentId());
        employee.setCreateUser(ThreadLocalUtil.getCurrentId());

        employeeMapper.insert(employee);
    }
}
