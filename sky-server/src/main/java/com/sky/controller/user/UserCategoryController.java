package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user/category")
public class UserCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public Result<List<Category>> getCategory(Integer type){
        log.info("微信用户端分类查询:{}",type);
        List<Category> categories = categoryService.selectCategoryByType(type);

        return Result.success(categories);
    }

}
