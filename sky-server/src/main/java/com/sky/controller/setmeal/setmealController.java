package com.sky.controller.setmeal;

import com.sky.result.PageResult;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
@Api("套餐操作")
public class setmealController {
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    private Result<PageResult> page(){


        return   Result.success();
    }
}
