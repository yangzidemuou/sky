package com.sky.controller.dish;

import com.sky.dto.DishPageDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 菜品起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    private Result startAndStopDish(@PathVariable Integer status,Long id){
        log.info("菜品起售停售：{}  {}",status,id);
        dishService.startAndStopDish(status,id);
        return Result.success();
    }


}