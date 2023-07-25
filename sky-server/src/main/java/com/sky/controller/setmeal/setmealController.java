package com.sky.controller.setmeal;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.setmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        log.info("套餐分类查询:{}",setmealPageDTO);
        PageResult pageResult = setmealService.page(setmealPageDTO);
        return   Result.success(pageResult);
    }


    @PostMapping
    @ApiOperation("新增套餐")
    private Result add(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}",setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success();
    }


    @PostMapping("/status/{status}")
    private Result changeStatus(@PathVariable Integer status,Long id){
        log.info("修改状态：{}，{}",status,id);
        setmealService.changeStatus(status,id);
        return Result.success();
    }
    @PutMapping
    @ApiOperation("套餐修改")
    private Result update(@RequestBody SetmealDTO setmealDTO){

        log.info("套餐修改：{}",setmealDTO);

        setmealService.update(setmealDTO);

        return  Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    private Result<SetmealVO> selectById(@PathVariable Long id){
        log.info("根据id查询套餐：{}",id);
        SetmealVO setmealVO= setmealService.selectById(id);
        return Result.success(setmealVO);
    }
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    private Result Delete(Long[] ids){
        log.info("批量删除套餐：{}", (Object) ids);

        setmealService.delete(ids);

        return Result.success();
    }
}
