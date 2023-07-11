package com.sky.web.admin;

import cn.hutool.core.bean.BeanUtil;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result login(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.login(employeeDTO);

        EmployeeLoginVO vo = BeanUtil.copyProperties(employee,EmployeeLoginVO.class);

        HashMap<String,Object> map = new HashMap<>();
        map.put("empId",employee.getId());
        String token= JwtUtil.createJWT(jwtProperties.getAdminSecret(),map);
        vo.setToken(token);
        return Result.success(vo);
    }


}
