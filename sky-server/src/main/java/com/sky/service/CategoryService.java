package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    PageResult page(CategoryDTO categoryDTO);

    /**
     * 添加分类
     * @param categoryDTO
     */
    void addCategory(CategoryDTO categoryDTO);

    /**
     * 修改分类
     * @param categoryDTO
     */
    void changeCategory(CategoryDTO categoryDTO);

    /**
     * 修改分类状态
     * @param status
     * @param id
     */
    void startAndStopCategory(Integer status, Long id);
}
