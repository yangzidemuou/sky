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

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteCategory(Long id);

    /**
     * 根据type查询分类信息
     * @param type
     * @return
     */
    PageResult selectCategoryByType(Integer type);
}
