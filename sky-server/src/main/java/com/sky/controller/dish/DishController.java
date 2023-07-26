package com.sky.controller.dish;

import com.sky.dto.DishPageDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        log.info("分页查询菜品数据:{}",dishPageDTO);
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

    /**
     * 根据type查询菜品信息
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据type查询菜品")
    private Result<List<Dish>> selectByTypeDish(Integer categoryId){
        log.info("根据菜品分类id查询菜品：{}",categoryId);
        List<Dish> dishes=dishService.selectByTypeDish(categoryId);
        return  Result.success(dishes);
    }

    /**
     * 根据id查询菜品信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    private Result<List<DishVO>> selectByIdDish(@PathVariable Long id){
        log.info("根据id查询菜品信息：{}",id);
        List<DishVO> dishVOS=dishService.selectByIdDish(id);
        return Result.success(dishVOS);
    }
    @PostMapping
    @ApiOperation("新增菜品")
    private Result addDish(@RequestBody DishVO dishVO){
        log.info("新增菜品：{}",dishVO);
        dishService.addDish(dishVO);
        return Result.success();
    }
    @DeleteMapping
    private Result deleteBatch(Long[] ids){
        dishService.deleteBatch(ids);
        return Result.success();
    }


}
