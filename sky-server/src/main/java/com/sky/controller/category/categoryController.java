package com.sky.controller.category;

import com.sky.dto.CategoryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/category")
@Api("分类操作")
public class categoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 分页查询分类数据
     */
    @GetMapping("/page")
    @ApiOperation("分页查询分类数据")
    public Result<PageResult> page(CategoryDTO categoryDTO){
        log.info("分页数据查询:{}",categoryDTO);
        PageResult pageResult = categoryService.page(categoryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增分类
     */
    @PostMapping
    @ApiOperation("添加分类")
    public Result<String> addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类:{}",categoryDTO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 修改分类
     */
    @PutMapping
    @ApiOperation("修改分类")
    private Result<String> changeCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类:{}",categoryDTO);
        categoryService.changeCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 修改分类状态
     */
    @PostMapping("status/{status}")
    @ApiOperation("分类状态修改")
    private Result<String> startAndStopCategory(@PathVariable Integer status,Long id){
        log.info("启用禁用分类");
        categoryService.startAndStopCategory(status,id);
        return Result.success();
    }

    /**
     * 根据id删除分类
     */
    @DeleteMapping
    @ApiOperation("根据id删除分类")
    private Result<String> deleteCategory(Long id){
        log.info("根据id查询分类:{}",id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

    /**
     *
     */
    @GetMapping("/list")
    @ApiOperation("根据id查询分类信息")
    private Result<List<Category>> selectCategoryByType(Integer type){
        log.info("根据id查询分类信息:{}",type);
        List<Category> list = categoryService.selectCategoryByType(type);
        return Result.success(list);
    }
}
