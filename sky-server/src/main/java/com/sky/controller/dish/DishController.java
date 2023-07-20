package com.sky.controller.dish;

import com.sky.dto.DishPageDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Api("菜品相关操作")
public class DishController {

    @Autowired
    private DishService dishService;


    @GetMapping("/page")
    @ApiOperation("分页查询菜品数据")
    private Result<PageResult> page(DishPageDTO dishPageDTO){
        log.info("分页查询菜品数据");
        PageResult pageResult=dishService.page(dishPageDTO);
        return Result.success(pageResult);
    }


}
