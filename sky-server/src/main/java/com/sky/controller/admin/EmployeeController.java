package com.sky.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/employee")
@Api("用户操作")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    JwtProperties jwtProperties;
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeDTO employeeDTO){

        log.info("员工登录:{}",employeeDTO.getUsername());
        Employee employee = employeeService.login(employeeDTO);

        EmployeeLoginVO vo = BeanUtil.copyProperties(employee,EmployeeLoginVO.class);

        HashMap<String,Object> map = new HashMap<>();
        map.put("empId",employee.getId());
        String token= JwtUtil.createJWT(jwtProperties.getAdminSecret(),map);
        vo.setToken(token);
        return Result.success(vo);
    }

    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public Result<String> logout(){
        return Result.success();
    }

    @PostMapping
    @ApiOperation("新增员工")
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工:{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询，参数为:{}",employeePageQueryDTO);
        PageResult pageResult= employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


}
