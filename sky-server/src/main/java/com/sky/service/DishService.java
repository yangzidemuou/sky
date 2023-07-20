package com.sky.service;

import com.sky.dto.DishPageDTO;
import com.sky.result.PageResult;

public interface DishService {
    /**
     * 分页查询菜品数据
     * @param dishPageDTO
     * @return
     */
    PageResult page(DishPageDTO dishPageDTO);

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    void startAndStopDish(Integer status, Long id);
}