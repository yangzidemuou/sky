package com.sky.controller.category;

import com.sky.dto.CategoryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/category")
@Api("分类操作")
public class categoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询分类数据
     * @param categoryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询分类数据")
    public Result<PageResult> page(CategoryDTO categoryDTO){
        PageResult pageResult = categoryService.page(categoryDTO);
        return Result.success(pageResult);
    }


}
