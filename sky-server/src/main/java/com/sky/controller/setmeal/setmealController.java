package com.sky.controller.setmeal;

import com.sky.dto.SetmealPageDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.setmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
@Api("套餐操作")
public class setmealController {
    @Autowired
    private setmealService setmealService;
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    private Result<PageResult> page(SetmealPageDTO setmealPageDTO){
        PageResult pageResult = setmealService.page(setmealPageDTO);
        return   Result.success(pageResult);
    }
}
